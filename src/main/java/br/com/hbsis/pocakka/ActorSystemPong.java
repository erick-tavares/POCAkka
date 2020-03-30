package br.com.hbsis.pocakka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;

public class ActorSystemPong {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("ActorSystemPong", ConfigFactory.load().getConfig("ActorSystemPong"));

        ActorRef actorPongRef = actorSystem.actorOf(ActorPong.props(), "actorPong");

        actorSystem.getWhenTerminated();
    }
}
