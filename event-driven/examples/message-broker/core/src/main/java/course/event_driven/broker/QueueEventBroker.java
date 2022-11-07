package course.event_driven.broker;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import course.event_driven.common.Event;
import course.event_driven.common.EventBroker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class QueueEventBroker implements EventBroker {

    private Connection connection;
    private Channel channel;

    private String queueName;

    public QueueEventBroker() {
        initialize();
    }

    @Override
    public void dispatch(Event event) throws Exception {

        if(channel == null) throw new Exception("Canal no inicializado");

        //Declarar la cola a la cual enviaremos el mensaje.
        channel.queueDeclare(queueName, false, false, false, null);
        String message = serializeMessage(event);
        channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println("Mensaje enviado");

    }

    @Override
    public void dispose() throws Exception{
        channel.close();
        connection.close();
    }

    private void initialize(){
        ConnectionFactory factory = new ConnectionFactory();
        //La conexión se realizará con un bróker en la máquina local.
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            queueName = "default";
        } catch (IOException | TimeoutException exception) {
            exception.printStackTrace();
        }

    }

    private String serializeMessage(Event event) {

        Gson json = new Gson();
        return json.toJson(event);

    }
}
