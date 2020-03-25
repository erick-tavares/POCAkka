package br.com.hbsis.pocakka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ActorPing extends UntypedAbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    int contador = 0;
    private ActorRef actorPongRef = getContext().actorOf(Props.create(ActorPong.class), "ActorPong");

    @Override
    public void onReceive(Object mensagem) throws Throwable {
        if (mensagem instanceof MailBox.PingMensagem) {
            log.info("Iniciar");
            actorPongRef.tell(new MailBox.PingMensagem("Ping"), getSelf());

        } else if (mensagem instanceof MailBox.PongMensagem) {
            MailBox.PongMensagem actorPong = (MailBox.PongMensagem) mensagem;
            log.info("Mensagem recebida: {} ", actorPong.getMensagem());
            contador += 1;

            if (contador == 5) {
                getContext().system().terminate();

            } else {
                getSender().tell(new MailBox.PingMensagem("Ping"), getSelf());
            }
        } else {
            unhandled(mensagem);
        }
    }
}
