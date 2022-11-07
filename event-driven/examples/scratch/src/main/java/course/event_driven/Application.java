package course.event_driven;

import course.event_driven.business.UserService;
import course.event_driven.common.events.UserEvent;
import course.event_driven.core.EventBroker;
import course.event_driven.entities.User;
import course.event_driven.notifications.UserNotifier;

public class Application {

    public static void main(String[] args) {

        //Creamos una instancia del broker.
        EventBroker dispatcher = new EventBroker();
        //Registramos a UserNotifier como un objeto interesado en escuchar eventos UserEvent.
        dispatcher.register(UserEvent.class, new UserNotifier());

        UserService service = new UserService();
        //Inyectamos el broker como dependencia. Así el servicio no tiene que depender de la clase Application.
        service.setBroker(dispatcher);

        User user = new User("Jack", "Sparrow", "jack@pirate.com");
        //La llamada a este método disparará el evento.
        service.update(user);

    }

}
