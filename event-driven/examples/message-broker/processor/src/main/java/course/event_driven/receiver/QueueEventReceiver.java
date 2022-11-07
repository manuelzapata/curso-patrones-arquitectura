package course.event_driven.receiver;

import com.google.gson.Gson;
import com.rabbitmq.client.*;
import course.event_driven.common.Event;
import course.event_driven.common.EventHandler;
import course.event_driven.common.EventReceiver;
import course.event_driven.common.events.UserEvent;
import course.event_driven.notifications.UserNotifier;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class QueueEventReceiver implements EventReceiver {

    private Connection connection;
    private Channel channel;

    private String queueName;

    public QueueEventReceiver() {
        initialize();
    }

    public void start() throws Exception {

        if(channel == null) throw new Exception("Canal no inicializado");
        channel.queueDeclare(queueName, false, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {

                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Recibido - " + message);

                //TODO: Mejorar el código para que soporte múltiples eventos
                Gson json = new Gson();
                UserEvent userEvent = json.fromJson(message, UserEvent.class);
                UserNotifier notifier = new UserNotifier();
                notifier.handle(userEvent);

            }

        };

        channel.basicConsume(queueName, true, consumer);
   }

    private void initialize(){
        ConnectionFactory factory = new ConnectionFactory();
        //La conexión se realizará con un broker en la máquina local.
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            queueName = "default";
        } catch (IOException | TimeoutException exception) {
            exception.printStackTrace();
        }

    }
}
