package space;

import space.utilities.LogsListener;
import space.utilities.MailboxSender;
import space.utilities.Utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Admin {
    static String id = "ADM"+ Utils.getRandomId();
    static MailboxSender mailboxSender = new MailboxSender();


    public static void main(String[] argv) throws Exception {
        System.out.println("ADMIN id="+id);

        LogsListener logsListener = new LogsListener();
        logsListener.start();


        Scanner scanner = new Scanner(System.in);
        while(true){
            sendNewMailboxMessage(scanner);
        }

    }


    private static boolean sendNewMailboxMessage(Scanner scanner) throws Exception{

        String line = scanner.nextLine();
        String regex = "\\s*(\\w+)\\s*([^\\s].*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {

            String receiver = matcher.group(1);
            String message = matcher.group(2);

            if(receiver.equals("all")){
                mailboxSender.send("all",message);

            }else if(receiver.equals("agencies")){
                mailboxSender.send("agency",message);
            }else if(receiver.equals("carriers")){
                mailboxSender.send("carrier",message);
            }else{
                printErrorInformation();
            }
            return true;
        }
        return false;
    }

    private static void printErrorInformation() {
        String information = "---------------------- \n" +
                "<receiver> <message>\n" +
                "all this message will be send to everyone\n" +
                "agencies this message will be send to each agency\n" +
                "carriers this message will be send to all carriers\n"+
                "----------------------\n";

        System.out.print(information);


    }



}
