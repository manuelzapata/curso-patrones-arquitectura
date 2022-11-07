package course.event_driven;

import course.event_driven.common.EventReceiver;
import course.event_driven.receiver.QueueEventReceiver;

public class Application {

    public static void main(String[] args) {

        EventReceiver receiver = new QueueEventReceiver();
        try {
            System.out.println("Aplicación iniciada");
            receiver.start();
        } catch (Exception exception) {
            System.out.println("No se puedo inicializar el receptor de eventos.");
            exception.printStackTrace();
        }


    }

}
