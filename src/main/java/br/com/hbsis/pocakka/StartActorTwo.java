package br.com.hbsis.pocakka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class StartActorTwo {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("ActorPong");

        ActorRef actorRef = actorSystem.actorOf(Props.create(EcoActorOne.class), "eco2");

        actorRef.tell("Log ActorOne: ", actorRef);
    }
}
