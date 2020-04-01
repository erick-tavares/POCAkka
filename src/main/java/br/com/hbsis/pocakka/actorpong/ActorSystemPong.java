package br.com.hbsis.pocakka.actorpong;

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
public class ActorSystemPong {

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(ActorSystemPong.class, args);
    }

    @PostConstruct
    void init() {
        ActorSystem actorSystem = ActorSystem.create("ActorSystemPong", ConfigFactory.load().getConfig("ActorSystemPong"));

        SpringExtension.getInstance().get(actorSystem).initialize(context);
        //  ActorRef actorPongRef = actorSystem.actorOf(ActorPong.props(), "actorPong");
        ActorRef actorPongRef = actorSystem.actorOf(SpringProps.create(actorSystem, ActorPong.class), "actorPong");

        actorSystem.getWhenTerminated();
    }

}
