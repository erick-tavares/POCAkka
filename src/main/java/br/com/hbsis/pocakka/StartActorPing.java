package br.com.hbsis.pocakka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class StartActorPing {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("ActorPing");

        ActorRef actorRef = actorSystem.actorOf(Props.create(EcoActorPong.class),"actorPong");

        actorRef.tell("Ping", actorRef);
    }
}
