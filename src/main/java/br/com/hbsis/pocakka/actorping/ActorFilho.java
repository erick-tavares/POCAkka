package br.com.hbsis.pocakka.actorping;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import br.com.hbsis.pocakka.config.Actor;
import protobuf.ErroMensagem;
import protobuf.FilhoMensagem;
import protobuf.PingMensagem;

@Actor
public class ActorFilho extends UntypedAbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    FilhoMensagem filho = FilhoMensagem.newBuilder().setMensagem("Ok").build();
    ErroMensagem erro = ErroMensagem.newBuilder().setMensagem(new NullPointerException().toString()).build();

    @Override
    public void onReceive(Object mensagem) throws Throwable {

        if (mensagem instanceof PingMensagem) {
            PingMensagem actorPing = (PingMensagem) mensagem;
            log.info("Mensagem recebida: {} ", actorPing.getMensagem());
            getSender().tell(filho, getSelf());

        } else if (mensagem instanceof ErroMensagem) {
            ErroMensagem error = (ErroMensagem) mensagem;
            log.info("Mensagem recebida: {} ", error.getMensagem());
            getSender().tell(erro, getSelf());
        }
    }
}