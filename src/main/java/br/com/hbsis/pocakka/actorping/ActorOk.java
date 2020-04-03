package br.com.hbsis.pocakka.actorping;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import br.com.hbsis.pocakka.MailBox;
import br.com.hbsis.pocakka.config.Actor;

@Actor
public class ActorOk extends UntypedAbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public void onReceive(Object mensagem) throws Throwable {

        if (mensagem instanceof MailBox.PingMensagem) {
            MailBox.PingMensagem actorPing = (MailBox.PingMensagem) mensagem;
            log.info("Mensagem recebida: {} ", actorPing.getMensagem());
            getSender().tell(new MailBox.OlaOk("Ok"), getSelf());

        } else if (mensagem instanceof MailBox.Error) {
            MailBox.Error error = (MailBox.Error) mensagem;
            log.info("Mensagem recebida: {} ", error.getMensagem());
            getSender().tell(new MailBox.Error(new NullPointerException()), getSelf());
        }
    }
}