package br.com.hbsis.pocakka.actorpong;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import br.com.hbsis.pocakka.actorpong.ActorPong;
import com.typesafe.config.ConfigFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActorSystemPong {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("ActorSystemPong", ConfigFactory.load().getConfig("ActorSystemPong"));

        ActorRef actorPongRef = actorSystem.actorOf(ActorPong.props(), "actorPong");

        actorSystem.getWhenTerminated();
    }
}
