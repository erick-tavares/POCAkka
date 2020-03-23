package br.com.hbsis.pocakka;

import akka.actor.ActorSystem;

public class StartActorTwo {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("Pong");
    }
}
