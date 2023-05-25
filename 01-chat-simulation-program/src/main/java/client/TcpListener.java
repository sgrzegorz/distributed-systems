package client;

import java.io.BufferedReader;

public class TcpListener extends Thread{
    BufferedReader in;

    public TcpListener(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {

            String msg;

            while ((msg = in.readLine()) != null) {
                System.out.println(msg);
            }


        }catch(Exception e){
            System.out.println("TcpListener exception");
        }
    }

}
