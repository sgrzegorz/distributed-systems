package space;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import space.utilities.LogsSender;
import space.utilities.Utils;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AgencyTaskDistributor extends Thread {
    private static final String EXCHANGE_NAME = "CARRIERSTASKS";
    static ConnectionFactory factory;
    String id;

    public AgencyTaskDistributor(String id) {
        this.id = id;
    }

    public void run(){
        try {

            LogsSender logsSender = new LogsSender();
            factory = new ConnectionFactory();
            factory.setHost("localhost");

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                String routingKey = getTaskFromInput(scanner);
                String message = id + " " + Utils.getRandomNumberInRange(1, 10000);


                channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
                logsSender.sendToAdmin(message);

            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getTaskFromInput(Scanner scanner) {
        while (true) {
            String[] inputLine = scanner.nextLine().split(" ");
            if (inputLine.length == 1 &&!inputLine[0].equals("")) {
                if(firstArgumentRecognised(inputLine[0])){
                    return inputLine[0];
                }

            }

            printInputInformation();

        }

    }

    private boolean firstArgumentRecognised(String arg){
        return (arg.equals("load") || arg.equals("people") || arg.equals("satelite")) ? true : false;
    }

    private void printInputInformation() {
        String information = "----------------------\n"+
                "Type valid service name:  \n"+
                "people\n"+
                "load \n" +
                "satelite \n" +
                "---------------------- \n";

        System.out.println(information);

    }




}
