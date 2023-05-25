package space.utilities;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MailboxSender {
    static ConnectionFactory factory;
    private static final String EXCHANGE_NAME = "MAILBOX_TOPIC_EXCHANGE";

    public MailboxSender() {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
    }

    public void send(String routingKey,String message) throws Exception{
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println("[Sent " +routingKey + "] '" + message + "'");
        }
    }



}
