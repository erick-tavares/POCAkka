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
            new OneForOneStrategy(3, Duration.create(1, TimeUnit.SECONDS),
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
                            } else {
                                log.info("escalate");
                                return SupervisorStrategy.escalate();
                            }
                        }
                    });

    ErroMensagem erro = ErroMensagem.newBuilder().setMensagem(new NullPointerException().toString()).build();
    private final ActorRef actorPongBaixoRef = getContext().actorOf(SpringProps.create(getContext().system(), ActorPongBaixo.class), "actorPongBaixo");
    private final ActorRef actorPongNormalRef = getContext().actorOf(SpringProps.create(getContext().system(), ActorPongNormal.class), "actorPongNormal");
    private final ActorRef actorPongAltoRef = getContext().actorOf(SpringProps.create(getContext().system(), ActorPongAlto.class), "actorPongAlto");

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
        } else {
            throw new IllegalArgumentException();
        }
    }
}
