package course.event_driven.core;

import course.event_driven.common.Event;
import course.event_driven.common.EventHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Es el componente de mensajería principal de la aplicación.
 *
 * Se encarga de registrar los objetos interesados en escuchar eventos.
 *
 * Además, se encarga de disparar los eventos.
 *
 * */
public class EventBroker {

    private Map<Class<? extends Event>,
            EventHandler<? extends Event>> handlers;

    public EventBroker() {
        handlers = new HashMap<>();
    }

    /**
     * Registra un procesador (handler) para que sea notificado cuando el evento ocurra.
     * */
    public <E extends Event> void register(Class<E> eventType, EventHandler<E> handler) {
        handlers.put(eventType, handler);
    }

    /**
     * Este método invoca el procesador registrado para el tipo de evento.
     * */
    public <E extends Event> void dispatch(E event) {

        EventHandler<E> handler = (EventHandler<E>)handlers.get(event.getClass());
        if(handler != null) {
            handler.handle(event);
        }
    }

}
