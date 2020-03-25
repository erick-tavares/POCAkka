package br.com.hbsis.pocakka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ActorSystemPong {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("ActorSystemPong");

        ActorRef actorPongRef = actorSystem.actorOf(Props.create(ActorPong.class), "ActorPong");

        actorPongRef.tell(new MailBox.PongMensagem("Pong"), ActorRef.noSender());
        actorSystem.getWhenTerminated();
    }
}
