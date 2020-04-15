package br.com.hbsis.pocakka.actorpong;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import br.com.hbsis.pocakka.config.Actor;
import protobuf.PingMensagem;


@Actor
public class ActorPongAlto extends UntypedAbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public void onReceive(Object mensagem) throws Throwable {

        if (mensagem instanceof PingMensagem) {
            log.info("Mensagem recebida: {} ", mensagem);
            log.info("FIM!!");
        } else {
            unhandled(mensagem);
        }
    }
}
