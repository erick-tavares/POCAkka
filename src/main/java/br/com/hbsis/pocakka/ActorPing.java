package br.com.hbsis.pocakka;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ActorPing extends UntypedAbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);


    public static Props props() {
        return Props.create(ActorPing.class);
    }

     ActorSelection actorPongRef = getContext().actorSelection("akka.tcp://ActorSystemPong@127.0.0.1:2553/user/actorPong");

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
