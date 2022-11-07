package course.event_driven.business;

import course.event_driven.core.EventBroker;

/**
 * Clase base para todos los servicios.
 *
 * Se le conoce como un layer supertype.
 *
 * */
public abstract class Service {

    EventBroker broker;

    /**
     * Inyección de dependencia.
     * */
    public void setBroker(EventBroker broker) {

        this.broker = broker;

    }

}
