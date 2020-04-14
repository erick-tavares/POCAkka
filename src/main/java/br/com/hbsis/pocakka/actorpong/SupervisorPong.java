package br.com.hbsis.pocakka.actorpong;

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
                            } else {
                                log.info("escalate");
                                return SupervisorStrategy.escalate();
                            }
                        }
                    });
    ErroMensagem erro = ErroMensagem.newBuilder().setMensagem(new NullPointerException().toString()).build();
    private ActorRef actorPongRef = getContext().actorOf(SpringProps.create(getContext().system(), ActorPong.class), "actorPong");

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object mensagem) throws Throwable {

        if (mensagem instanceof PingMensagem) {
            if (((PingMensagem) mensagem).getNivel().equals(Nivel.BAIXO)) {
                actorPongRef.tell(mensagem, getSender());
            }
        } else if (mensagem instanceof ErroMensagem) {
            unhandled(mensagem);
            getSelf().tell(erro, getSelf());
        }
    }
}
