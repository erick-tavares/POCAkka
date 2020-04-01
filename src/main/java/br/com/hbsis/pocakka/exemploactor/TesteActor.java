package br.com.hbsis.pocakka.exemploactor;

import akka.actor.AbstractActor;
import br.com.hbsis.pocakka.config.Actor;
import org.springframework.beans.factory.annotation.Autowired;

@Actor
public class TesteActor extends AbstractActor {

    @Autowired
    private MessageService messageService;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, messageService :: print)
                .build();
    }
}
