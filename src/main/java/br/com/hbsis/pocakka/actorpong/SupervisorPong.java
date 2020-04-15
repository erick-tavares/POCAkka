package br.com.hbsis.pocakka.actorpong;

import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.protobuf.UninitializedMessageException;
import br.com.hbsis.pocakka.config.Actor;
import br.com.hbsis.pocakka.config.SpringProps;
import protobuf.ErroMensagem;
import protobuf.Nivel;
import protobuf.PingMensagem;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

@Actor
public class SupervisorPong extends UntypedAbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    public SupervisorStrategy strategy =
            new OneForOneStrategy(3, Duration.create(5, TimeUnit.SECONDS),
                    new akka.japi.Function<Throwable, SupervisorStrategy.Directive>() {

                        @Override
                        public SupervisorStrategy.Directive apply(Throwable e) {
                            if (e instanceof NullPointerException) {
                                log.info("Supervisor Strategry error: " + e.getMessage(), e);
                                return SupervisorStrategy.restart();
                            }
                            if (e instanceof IllegalArgumentException) {
                                log.info("Supervisor Strategry error: " + e.getMessage(), e);
                                return SupervisorStrategy.stop();
                            }
                            else {
                                log.info("escalate");
                                return SupervisorStrategy.escalate();
                            }
                        }
                    });
    ErroMensagem erro = ErroMensagem.newBuilder().setMensagem(new NullPointerException().toString()).build();
    private ActorRef actorPongBaixoRef = getContext().actorOf(SpringProps.create(getContext().system(), ActorPongBaixo.class), "actorPongBaixo");
    private ActorRef actorPongNormalRef = getContext().actorOf(SpringProps.create(getContext().system(), ActorPongNormal.class), "actorPongNormal");
    private ActorRef actorPongAltoRef = getContext().actorOf(SpringProps.create(getContext().system(), ActorPongAlto.class), "actorPongAlto");

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object mensagem) throws Throwable {

        if (mensagem instanceof PingMensagem) {
            if (((PingMensagem) mensagem).getNivel().equals(Nivel.BAIXO)) {
                actorPongBaixoRef.tell(mensagem, getSender());
            }
            if (((PingMensagem) mensagem).getNivel().equals(Nivel.NORMAL)) {
                actorPongNormalRef.tell(mensagem, getSender());
            }
            if (((PingMensagem) mensagem).getNivel().equals(Nivel.ALTO)) {
                actorPongAltoRef.tell(mensagem, getSender());
            }
        }
        else {
            unhandled(mensagem);
            getSelf().tell(new NullPointerException(), getSelf());
        }
    }
}
