package br.com.hbsis.pocakka.actorping;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import br.com.hbsis.pocakka.config.Actor;
import br.com.hbsis.pocakka.config.SpringProps;
import protobuf.ErroMensagem;
import protobuf.FilhoMensagem;
import protobuf.PingMensagem;
import protobuf.PongMensagem;
import scala.concurrent.duration.Duration;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


@Actor
public class ActorPing extends UntypedAbstractActor {

    Scanner s = new Scanner(System.in);
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

    PingMensagem ping = PingMensagem.newBuilder().setMensagem("Ping").build();
    ErroMensagem erro = ErroMensagem.newBuilder().setMensagem(new NullPointerException().toString()).build();

    private ActorSelection actorPongRef = getContext().actorSelection("akka.tcp://ActorSystemPong@127.0.0.1:2553/user/actorPong");
    private ActorRef actorFilhoRef = getContext().actorOf(SpringProps.create(getContext().system(), ActorFilho.class), "actorFilho");

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object mensagem) throws Throwable {

        if (mensagem instanceof PingMensagem) {
            log.info("Iniciar");
            mensagem = s.next();

            if (mensagem.equals("pong")) {
                actorPongRef.tell(ping, getSelf());
            } else if (mensagem.equals("filho")) {
                actorFilhoRef.tell(ping, getSelf());
            } else {
                actorFilhoRef.tell(erro, getSelf());
            }
        } else if (mensagem instanceof PongMensagem) {
            PongMensagem actorPong = (PongMensagem) mensagem;
            log.info("Mensagem recebida: {} ", actorPong.getMensagem());

        } else if (mensagem instanceof FilhoMensagem) {
            FilhoMensagem filhoMensagem = (FilhoMensagem) mensagem;
            log.info("Mensagem recebida: {} ", filhoMensagem.getMensagem());
        } else if (mensagem instanceof ErroMensagem) {
            unhandled(mensagem);
        }
    }
}
