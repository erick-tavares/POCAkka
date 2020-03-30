package br.com.hbsis.pocakka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;


public class ActorSystemPing {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("ActorSystemPing", ConfigFactory.load().getConfig("ActorSystemPing"));

        ActorRef actorPingRef = actorSystem.actorOf(ActorPing.props(), "actorPing");

        actorPingRef.tell(new MailBox.PingMensagem("Mensagem"), ActorRef.noSender());

        actorSystem.getWhenTerminated();
    }
}

