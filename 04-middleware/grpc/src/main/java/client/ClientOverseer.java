package client;

public class ClientOverseer {

    public static volatile boolean  recovery =false;

    public static void main(String [] args) throws Exception {
        if(args.length<=0 || args.length>3) printInformation();
        for (String input : args){

            if(input.equals("storm") || input.equals("vulc") || input.equals("wind")){
                System.out.println(input);
            }else{
                printInformation();
            }
        }

        NewClient newClient = new NewClient(args);
        newClient.start();
        while(true){
            if(recovery){
                System.out.println("Client tries to reconnect");
                newClient.interrupt();
                newClient = new NewClient(args);
                newClient.start();
                recovery =false;
            }
            Thread.sleep(5000);
        }

    }

    public static void printInformation(){
        System.out.println("usage: wind vulc storm");
        System.exit(1);
    }
}
