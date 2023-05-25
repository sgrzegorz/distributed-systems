package space;

import com.rabbitmq.client.ConnectionFactory;
import space.utilities.MailBoxListener;
import space.utilities.Utils;

public class Agency {



    private static final String CARRIERSTASKS_EXCHANGE_NAME = "CARRIERSTASKS";
    static ConnectionFactory factory;
    static String id = "AGE"+ Utils.getRandomId();


    public static void main(String[] argv) throws Exception {
        System.out.println("AGENCY id="+id);
        factory = new ConnectionFactory();
        factory.setHost("localhost");


        AgencyTaskDistributor agencyTaskDistributor= new AgencyTaskDistributor(id);
        agencyTaskDistributor.start();

        MailBoxListener mailBoxListener = new MailBoxListener("agency",id);
        mailBoxListener.start();


    }







}
