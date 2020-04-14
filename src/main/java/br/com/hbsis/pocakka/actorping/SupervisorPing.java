package br.com.hbsis.pocakka.actorping;

import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import br.com.hbsis.pocakka.config.Actor;
import br.com.hbsis.pocakka.config.SpringProps;
import protobuf.ErroMensagem;
import protobuf.Nivel;
import protobuf.PingMensagem;
import protobuf.PongMensagem;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

@Actor
public class SupervisorPing extends UntypedAbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    public SupervisorStrategy strategy =
            new OneForOneStrategy(3, Duration.create(5, TimeUnit.SECONDS),
                    new akka.japi.Function<Throwable, SupervisorStrategy.Directive>() {

                        @Override
                        public SupervisorStrategy.Directive apply(Throwable e) {
                            if (e instanceof NullPointerException) {
                                log.info("Supervisor Strategry error: " + e.getMessage(), e);
                                return SupervisorStrategy.restart();
                            } else {
                                log.info("escalate");
                                return SupervisorStrategy.escalate();
                            }
                        }
                    });

    ErroMensagem erro = ErroMensagem.newBuilder().setMensagem(new NullPointerException().toString()).build();
    private ActorRef actorPingRef = getContext().actorOf(SpringProps.create(getContext().system(), ActorPing.class), "actorPing");

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object mensagem) throws Throwable {

        if (mensagem.equals("Iniciar")) {
            actorPingRef.tell(PingMensagem.newBuilder().setMensagem(mensagem.toString()).setNivel(Nivel.BAIXO).build(), getSelf());
        } else if (mensagem instanceof PongMensagem) {
            if (((PongMensagem) mensagem).getNivel().equals(Nivel.NORMAL)) {
                actorPingRef.tell(mensagem, getSender());
            }
        } else if (mensagem instanceof ErroMensagem) {
            unhandled(mensagem);
            getSelf().tell(erro, getSelf());

        }
    }
}