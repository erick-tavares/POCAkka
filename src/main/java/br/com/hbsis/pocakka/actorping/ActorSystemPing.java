package br.com.hbsis.pocakka.actorping;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import br.com.hbsis.pocakka.MailBox;
import br.com.hbsis.pocakka.actorping.ActorPing;
import com.typesafe.config.ConfigFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActorSystemPing {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("ActorSystemPing", ConfigFactory.load().getConfig("ActorSystemPing"));

        ActorRef actorPingRef = actorSystem.actorOf(ActorPing.props(), "actorPing");

        actorPingRef.tell(new MailBox.PingMensagem("Mensagem"), ActorRef.noSender());

        actorSystem.getWhenTerminated();
    }
}

