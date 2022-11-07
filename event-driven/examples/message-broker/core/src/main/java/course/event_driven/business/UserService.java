package course.event_driven.business;

import course.event_driven.common.events.UserEvent;
import course.event_driven.entities.User;

import java.util.Date;

/**
 * Este servicio actuará como un generador de eventos.
 * */
public class UserService extends Service {


    public void update(User user) {

        //Realizar validaciones...

        //Guardar usuario...

        //enviar notificación
        try {
            UserEvent event = new UserEvent(user, UserEvent.UserEventType.UPDATED, new Date());
            this.broker.dispatch(event);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}