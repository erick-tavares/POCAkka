package br.com.hbsis.pocakka;

import akka.actor.ActorSystem;

public class StartActorOne {

    public static void main(String[] args) {
ActorSystem actorSystem = ActorSystem.create("Ping");
    }
}
