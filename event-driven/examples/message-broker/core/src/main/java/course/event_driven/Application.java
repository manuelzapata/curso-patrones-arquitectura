package course.event_driven;

import course.event_driven.common.EventBroker;
import course.event_driven.broker.QueueEventBroker;
import course.event_driven.business.UserService;
import course.event_driven.entities.User;

public class Application {

    public static void main(String[] args) {

        EventBroker broker = new QueueEventBroker();

        UserService service = new UserService();
        //Inyectamos el broker como dependencia. Así el servicio no tiene que depender de la clase Application.
        service.setBroker(broker);

        User user = new User("Jack", "Sparrow", "jack@pirate.com");
        //La llamada a este método disparará el evento.
        service.update(user);

    }

}
