package br.com.hbsis.pocakka.actorping;

import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.DeciderBuilder;
import akka.protobuf.UninitializedMessageException;
import br.com.hbsis.pocakka.config.Actor;
import br.com.hbsis.pocakka.config.SpringProps;
import ch.qos.logback.classic.Logger;
import protobuf.ErroMensagem;
import protobuf.Nivel;
import protobuf.PingMensagem;
import protobuf.PongMensagem;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

@Actor
public class SupervisorPing extends UntypedAbstractActor {

   LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);


//    private static SupervisorStrategy strategy =
//            new OneForOneStrategy(3, Duration.create(5, TimeUnit.SECONDS),
//                    DeciderBuilder.match(ArithmeticException.class, e -> SupervisorStrategy.resume())
//                            .match(NullPointerException.class, e -> SupervisorStrategy.restart())
//                            .match(IllegalArgumentException.class, e -> SupervisorStrategy.stop())
//                            .matchAny(o -> SupervisorStrategy.escalate())
//                            .build());
    public SupervisorStrategy strategy =
            new OneForOneStrategy(3, Duration.create(5, TimeUnit.SECONDS),
                    new akka.japi.Function<Throwable, SupervisorStrategy.Directive>() {

                        @Override
                        public SupervisorStrategy.Directive apply(Throwable e) {
                            log.info("Strategy");
                            if (e instanceof NullPointerException) {
                                log.info("Supervisor Strategry error: " + e.getMessage(), e);
                                return SupervisorStrategy.restart();
                            }
                            if (e instanceof IllegalArgumentException){
                                log.info("Supervisor Strategry error: " + e.getMessage(), e);
                                return SupervisorStrategy.stop();
                            }
                            else {
                                log.info("escalate");
                                return SupervisorStrategy.escalate();
                            }
                        }
                    });

    ErroMensagem erro = ErroMensagem.newBuilder().setMensagem(new NullPointerException().toString() + "------------------------------------------------").build();
    private ActorRef actorPingBaixoRef = getContext().actorOf(SpringProps.create(getContext().system(), ActorPingBaixo.class), "actorPingBaixo");
    private ActorRef actorPingNormalRef = getContext().actorOf(SpringProps.create(getContext().system(), ActorPingNormal.class), "actorPingNormal");
    private ActorRef actorPingAltoRef = getContext().actorOf(SpringProps.create(getContext().system(), ActorPingAlto.class), "actorPingAlto");

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object mensagem) throws Throwable {

        if (mensagem.equals("Iniciar")) {
            actorPingBaixoRef.tell(PingMensagem.newBuilder().setMensagem(mensagem.toString()).setNivel(Nivel.BAIXO).build(), getSelf());
        } else if (mensagem instanceof PongMensagem) {
            if (((PongMensagem) mensagem).getNivel().equals(Nivel.BAIXO)) {
                actorPingBaixoRef.tell(mensagem, getSender());
            }
            if (((PongMensagem) mensagem).getNivel().equals(Nivel.NORMAL)) {
                actorPingNormalRef.tell(mensagem, getSender());
            }
            if (((PongMensagem) mensagem).getNivel().equals(Nivel.ALTO)) {
                actorPingAltoRef.tell(mensagem, getSender());
            }
        }
        else{
            unhandled(mensagem);
          //  supervisorStrategy().decider();
            getSelf().tell(new NullPointerException(), getSelf());

        }
    }
}