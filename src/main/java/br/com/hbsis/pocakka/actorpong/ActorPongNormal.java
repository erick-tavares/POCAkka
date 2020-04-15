package br.com.hbsis.pocakka.actorpong;

import akka.actor.ActorSelection;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import br.com.hbsis.pocakka.config.Actor;
import protobuf.Nivel;
import protobuf.PingMensagem;
import protobuf.PongMensagem;

@Actor
public class ActorPongNormal extends UntypedAbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    PongMensagem pong = PongMensagem.newBuilder().setMensagem("Pong").setNivel(Nivel.ALTO).build();
    ActorSelection actorPingRef = getContext().actorSelection("akka.tcp://ActorSystemPing@127.0.0.1:2552/user/supervisorPing");

    @Override
    public void onReceive(Object mensagem) throws Throwable {

        if (mensagem instanceof PingMensagem) {
            log.info("Mensagem recebida: {} ", mensagem);
            actorPingRef.tell(pong, getSelf());
        } else {
            unhandled(mensagem);
        }
    }
}