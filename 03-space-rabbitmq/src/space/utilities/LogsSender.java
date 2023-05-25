package space.utilities;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class LogsSender {
    ConnectionFactory factory;
    private static final String ADMIN_EXCHANGE_NAME = "ADMINLOG";


    public LogsSender() throws Exception{
        factory = new ConnectionFactory();
        factory.setHost("localhost");
    }


    public void sendToAdmin(String message) throws Exception {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(ADMIN_EXCHANGE_NAME, "fanout");

            channel.basicPublish(ADMIN_EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            if(Utils.debug) System.out.println("[Sent log] '" + message + "'");
        }
    }


}
