package br.com.hbsis.pocakka;

import akka.actor.*;
import akka.remote.ContainerFormats;
import com.typesafe.config.ConfigFactory;


public class ActorSystemPing {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("ActorSystemPing", ConfigFactory.load().getConfig("ActorSystemPing"));

        ActorRef actorPingRef = actorSystem.actorOf(ActorPing.props(), "actorPing");

        actorPingRef.tell(new MailBox.PingMensagem("Ping"), ActorRef.noSender());

        actorSystem.getWhenTerminated();
    }
}

