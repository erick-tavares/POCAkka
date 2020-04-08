package br.com.hbsis.pocakka.actorpong;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import br.com.hbsis.pocakka.config.Actor;
import protobuf.PingMensagem;
import protobuf.PongMensagem;

@Actor
public class ActorPong extends UntypedAbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    PongMensagem pong = PongMensagem.newBuilder().setMensagem("Pong").build();

    @Override
    public void onReceive(Object mensagem) throws Throwable {

        if (mensagem instanceof PingMensagem) {
            PingMensagem actorPing = (PingMensagem) mensagem;
            log.info("Mensagem recebida: {} ", actorPing.getMensagem());
            getSender().tell(pong, getSelf());
        } else {
            unhandled(mensagem);
        }
    }
}