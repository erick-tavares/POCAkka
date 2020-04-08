package br.com.hbsis.pocakka.actorping;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import br.com.hbsis.pocakka.MailBox;
import br.com.hbsis.pocakka.config.Actor;
import br.com.hbsis.pocakka.config.SpringProps;
import scala.concurrent.duration.Duration;

import java.util.Scanner;


@Actor
public class ActorPing extends UntypedAbstractActor {


    Scanner s = new Scanner(System.in);
    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    public SupervisorStrategy strategy =
            new OneForOneStrategy(3, Duration.create("5 second"),
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


    private ActorSelection actorPongRef = getContext().actorSelection("akka.tcp://ActorSystemPong@127.0.0.1:2553/user/actorPong");
    //    public static Props props() {
//        return Props.create(ActorPing.class);
//    }
    private ActorRef actorFilhoRef = getContext().actorOf(SpringProps.create(getContext().system(), ActorFilho.class), "actorFilho");


    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object mensagem) throws Throwable {

        if (mensagem instanceof MailBox.PingMensagem) {
            log.info("Iniciar");
            mensagem = s.next();

            if (mensagem.equals("pong")) {
                actorPongRef.tell(new MailBox.PingMensagem("Ping"), getSelf());
            } else if (mensagem.equals("filho")) {
                actorFilhoRef.tell(new MailBox.PingMensagem("Ping"), getSelf());
            } else {
                actorFilhoRef.tell(new MailBox.Error(new NullPointerException()), getSelf());
            }
        } else if (mensagem instanceof MailBox.PongMensagem) {
            MailBox.PongMensagem actorPong = (MailBox.PongMensagem) mensagem;
            log.info("Mensagem recebida: {} ", actorPong.getMensagem());

        } else if (mensagem instanceof MailBox.FilhoMensagem) {
            MailBox.FilhoMensagem filhoMensagem = (MailBox.FilhoMensagem) mensagem;
            log.info("Mensagem recebida: {} ", filhoMensagem.getMensagem());
        } else if (mensagem instanceof NullPointerException) {
            unhandled(mensagem);
        }
    }
}
