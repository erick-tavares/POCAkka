package br.com.hbsis.pocakka.actorpong;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import br.com.hbsis.pocakka.MailBox;
import br.com.hbsis.pocakka.config.Actor;

@Actor
public class ActorPong extends UntypedAbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public void onReceive(Object mensagem) throws Throwable {

        if (mensagem instanceof MailBox.PingMensagem) {
             MailBox.PingMensagem actorPing = (MailBox.PingMensagem) mensagem;
            log.info("Mensagem recebida: {} ", actorPing.getMensagem());
            getSender().tell(new MailBox.PongMensagem("Pong"), getSelf());
        } else {
            unhandled(mensagem);
        }
    }
}