package br.com.hbsis.pocakka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;
import scala.concurrent.Future;

public class ActorSystemPing {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("ActorSystemPing");

        ActorRef actorPingRef = actorSystem.actorOf(Props.create(ActorPing.class), "ActorPing");

        actorPingRef.tell(new MailBox.PingMensagem("Ping"), ActorRef.noSender());

        actorSystem.getWhenTerminated();
    }
}
