package br.com.hbsis.pocakka.exemploAbstractActor;

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
public class TesteSystem {

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(TesteSystem.class, args);
    }

    @PostConstruct
    void init() {
        ActorSystem system = ActorSystem.create("actor-system", ConfigFactory.load());
        SpringExtension.getInstance().get(system).initialize(context);

        ActorRef testActor = system.actorOf(SpringProps.create(system, TesteActor.class));

        testActor.tell("hello world", ActorRef.noSender());
    }
}
