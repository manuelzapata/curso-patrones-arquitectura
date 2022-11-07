package course.event_driven.common;

import course.event_driven.common.Event;

/**
 * Interfaz base para soportar brókers de mensajes en la aplicación.
 * */
public interface EventBroker {

    void dispatch(Event event) throws Exception;

    void dispose() throws Exception;

}
