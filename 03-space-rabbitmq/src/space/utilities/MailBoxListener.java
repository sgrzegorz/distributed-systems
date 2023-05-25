package space.utilities;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class MailBoxListener extends Thread {

    String customerType;
    String customerId;
    private static final String EXCHANGE_NAME = "MAILBOX_TOPIC_EXCHANGE";

    public MailBoxListener(String customerType, String customerId) {
        this.customerType = customerType;
        this.customerId = customerId;
    }

    public void run() {
        try {
            recive(customerType,customerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recive(String customerType, String customerId) throws Exception{

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName,EXCHANGE_NAME,customerId);
        channel.queueBind(queueName,EXCHANGE_NAME,customerType);
        channel.queueBind(queueName,EXCHANGE_NAME,"all");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            if(Utils.debug){
                System.out.println("[Received " +delivery.getEnvelope().getRoutingKey()+"]   "  + message + "'");
            }else{
                System.out.println("[Received] '"  + message + "'");

            }


        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

    }

}
