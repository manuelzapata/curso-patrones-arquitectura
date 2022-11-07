package course.event_driven.notifications;

import course.event_driven.common.EventHandler;
import course.event_driven.common.events.UserEvent;

/**
 * Implementación de un procesador o handler.
 * */
public class UserNotifier implements EventHandler<UserEvent> {

    public void handle(UserEvent event) {

        //Lógica para enviar correo....

        String format = "Evento recibido: %s, %tc, %s";
        System.out.format(format, event.getType(), event.getEventDate(), event.getUser().getEmail());

    }

}
