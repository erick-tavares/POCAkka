package br.com.hbsis.pocakka.actorping;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import br.com.hbsis.pocakka.MailBox;
import br.com.hbsis.pocakka.config.SpringExtension;
import br.com.hbsis.pocakka.config.SpringProps;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ActorSystemPing {

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(ActorSystemPing.class, args);
    }

    @PostConstruct
    void init() {
        ActorSystem actorSystem = ActorSystem.create("ActorSystemPing", ConfigFactory.load().getConfig("ActorSystemPing"));
        SpringExtension.getInstance().get(actorSystem).initialize(context);

        // ActorRef actorPingRef = actorSystem.actorOf(ActorPing.props(), "actorPing");
        ActorRef actorPingRef = actorSystem.actorOf(SpringProps.create(actorSystem, ActorPing.class), "actorPing");

        actorPingRef.tell(new MailBox.PingMensagem("Mensagem"), ActorRef.noSender());

        actorSystem.getWhenTerminated();
    }
}

