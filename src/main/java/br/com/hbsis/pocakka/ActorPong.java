package br.com.hbsis.pocakka;

import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ActorPong extends UntypedAbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props() {
        return Props.create(ActorPong.class);
    }

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