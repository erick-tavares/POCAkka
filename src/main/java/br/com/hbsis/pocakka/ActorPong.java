package br.com.hbsis.pocakka;

import akka.actor.ActorRef;
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

    //  private ActorRef actorPingRefA = getContext().actorOf(Props.create(ActorPing.class), "actorPing");
    ActorSelection actorPingRef = getContext().actorSelection("akka.tcp://ActorSystemPing@127.0.0.1:2552/user/actorPing");

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