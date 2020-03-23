package br.com.hbsis.pocakka;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class EcoActorPing extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object menssagem) throws Exception {
        log.info("Menssagem recebida: " + menssagem);
    }
}
