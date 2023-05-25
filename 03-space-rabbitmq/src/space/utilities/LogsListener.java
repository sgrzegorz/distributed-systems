package space.utilities;

import com.rabbitmq.client.*;

public class LogsListener extends Thread{

    private static final String ADMIN_EXCHANGE_NAME = "ADMINLOG";

    public void run(){
        try {
            receiveLogs();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void receiveLogs() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(ADMIN_EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, ADMIN_EXCHANGE_NAME, "");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("[received log] '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

    }



}
