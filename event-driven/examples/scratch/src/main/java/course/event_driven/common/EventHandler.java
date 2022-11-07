package course.event_driven.common;

/**
 * Interfaz que deben definir los objetos que desean ser notificados
 * de eventos.
 * */
public interface EventHandler <E extends Event> {

    void handle(E event);

}