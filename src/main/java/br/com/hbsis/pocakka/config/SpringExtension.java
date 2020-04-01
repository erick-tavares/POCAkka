package br.com.hbsis.pocakka.config;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;

public class SpringExtension extends AbstractExtensionId<SpringExtension.SpringExt> {

    private static SpringExtension instance = new SpringExtension();

    public static SpringExtension getInstance() {
        return instance;
    }

    @Override
    public SpringExt createExtension(ExtendedActorSystem system) {
        return new SpringExt();
    }

    public static class SpringExt implements Extension {
        private static ApplicationContext applicationContext;

        public void initialize(ApplicationContext applicationContext) {
            SpringExt.applicationContext = applicationContext;
        }

       public Props props(Class<? extends akka.actor.Actor> actorBeanClass) {
            return Props.create(SpringActorProducer.class, applicationContext, actorBeanClass);
        }

        public Props props(Class<? extends akka.actor.Actor> actorBeanClass, Object... parameters) {
            return Props.create(SpringActorProducer.class, applicationContext, actorBeanClass, parameters);
        }

    }
}
