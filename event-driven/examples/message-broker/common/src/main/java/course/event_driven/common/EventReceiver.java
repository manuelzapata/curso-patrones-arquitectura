package course.event_driven.common;

/**
 * Interfaz complementaria a EventBroker.
 *
 * Las clases que implementen esta interfaz, contendrán detalles específicos
 * a la cola de mensajes que se está soportando.
 *
 * */
public interface EventReceiver {

    void start() throws Exception;

}
