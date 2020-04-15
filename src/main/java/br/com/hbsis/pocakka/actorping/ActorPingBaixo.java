package br.com.hbsis.pocakka.actorping;

import akka.actor.ActorSelection;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import br.com.hbsis.pocakka.config.Actor;
import protobuf.ErroMensagem;
import protobuf.Nivel;
import protobuf.PingMensagem;
import protobuf.PongMensagem;

@Actor
public class ActorPingBaixo extends UntypedAbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    PingMensagem ping = PingMensagem.newBuilder().setMensagem("Ping").setNivel(Nivel.BAIXO).build();
    ActorSelection actorPongRef = getContext().actorSelection("akka.tcp://ActorSystemPong@127.0.0.1:2553/user/supervisorPong");

    @Override
    public void onReceive(Object mensagem) throws Throwable {

        if (mensagem instanceof PingMensagem) {
            log.info("Mensagem recebida: {} ", mensagem);
            actorPongRef.tell(ping, getSelf());
        }
        if (mensagem instanceof PongMensagem) {
            log.info("Mensagem recebida: {} ", mensagem);
            actorPongRef.tell(ping, getSelf());
        } else if (mensagem instanceof ErroMensagem) {
            unhandled(mensagem);
        }
    }
}
