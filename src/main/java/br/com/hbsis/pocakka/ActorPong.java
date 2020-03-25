package br.com.hbsis.pocakka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ActorPong extends UntypedAbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    int contador = 0;

    private ActorRef actorPingRef = getContext().actorOf(Props.create(ActorPing.class), "ActorPing");

    @Override
    public void onReceive(Object mensagem) throws Throwable {
        if (mensagem instanceof MailBox.PongMensagem) {
            actorPingRef.tell(new MailBox.PongMensagem("Pong"), getSelf());

        } else if (mensagem instanceof MailBox.PingMensagem) {
            MailBox.PingMensagem actorPing = (MailBox.PingMensagem) mensagem;
            log.info("Mensagem recebida: {} ", actorPing.getMensagem());
            contador += 1;

            if (contador == 5) {
                getContext().system().terminate();

            } else {
                getSender().tell(new MailBox.PongMensagem("Pong"), getSelf());
            }
        } else {
            unhandled(mensagem);
        }
    }
}