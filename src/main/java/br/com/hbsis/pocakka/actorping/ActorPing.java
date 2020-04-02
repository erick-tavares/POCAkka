package br.com.hbsis.pocakka.actorping;

import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import br.com.hbsis.pocakka.config.Actor;
import br.com.hbsis.pocakka.MailBox;


@Actor
public class ActorPing extends UntypedAbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    ActorSelection actorPongRef = getContext().actorSelection("akka.tcp://ActorSystemPong@127.0.0.1:2553/user/actorPong");

//    public static Props props() {
//        return Props.create(ActorPing.class);
//    }

    @Override
    public void onReceive(Object mensagem) throws Throwable {
        if (mensagem instanceof MailBox.PingMensagem) {
            log.info("Iniciar");
            actorPongRef.tell(new MailBox.PingMensagem("Ping"), getSelf());

        } else if (mensagem instanceof MailBox.PongMensagem) {
            MailBox.PongMensagem actorPong = (MailBox.PongMensagem) mensagem;
            log.info("Mensagem recebida: {} ", actorPong.getMensagem());
        } else {
            unhandled(mensagem);
        }
    }
}
