package br.com.hbsis.pocakka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class StartActorPong {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("ActorPong");

        ActorRef actorRef = actorSystem.actorOf(Props.create(EcoActorPing.class), "actorPin");

        actorRef.tell("Mensagem do ActorPong: ", actorRef);
    }
}
