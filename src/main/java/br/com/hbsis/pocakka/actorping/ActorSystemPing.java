package br.com.hbsis.pocakka.actorping;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
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

        ActorRef actorSuperRef = actorSystem.actorOf(SpringProps.create(actorSystem, SupervisorPing.class), "supervisorPing");

        actorSuperRef.tell("Iniciar", ActorRef.noSender());

        actorSystem.getWhenTerminated();
    }
}

