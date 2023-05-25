package space;

import com.rabbitmq.client.*;
import space.utilities.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Carrier {
    private static final String CARRIERSTASKS_EXCHANGE_NAME = "CARRIERSTASKS";
    static String id = "CAR" + Utils.getRandomId();
    //    static String[] queueNames =  new String[]{"people","load","satelite"};

    private static final Map<String, String> queueNames = new HashMap<>(){
        {
            put("people", "peopleQueue");
            put("load", "loadQueue");
            put("satelite", "sateliteQueue");
        }
    };


    public static void main(String[] args) throws Exception {
        checkArgs(args);
        String identity = "CARRIER id=" + id;
        for(String service : args){
            identity+=" "+service;
        }
        System.out.println(identity);


        MailBoxListener mailBoxListener = new MailBoxListener("carrier", id);
        mailBoxListener.start();

        MailboxSender mailboxSender = new MailboxSender();


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(CARRIERSTASKS_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);


        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("[received " + delivery.getEnvelope().getRoutingKey() + " ]   '" + message + "'");
            String messageParts [] = message.split(" +");
            String agencyId=messageParts[0], jobId=messageParts[1];

            System.out.println("[working on] jobid="+jobId);


            String reply = "carrier " +id + " ack ";
            try{
                mailboxSender.send(agencyId, reply);
                new LogsSender().sendToAdmin(reply);

            }catch (Exception e){
                e.printStackTrace();
            }

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);

        };


        for (String service : args) {
            String queueName = channel.queueDeclare(queueNames.get(service), true, false, false, null).getQueue();

            channel.queueBind(queueName, CARRIERSTASKS_EXCHANGE_NAME, service);

            channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {
            });

        }

    }

    private static void checkArgs(String [] args){
        if(args.length !=2){

            printInformation();
        }
        for(String s : args){
            if(!s.equals("people") && !s.equals("load") && !s.equals("satelite")) printInformation();
        }

    }



    private static void printInformation(){
        String information = "Program parameters should be two names from {people, load, satelite}\n" +
                "satelite load";
        System.out.println(information);
        System.exit(1);
    }



}
