package client;


import server.Action;
import server.Task;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UdpBroadcastListener extends Thread {


    DatagramSocket socket;


    public UdpBroadcastListener(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {


        try{

            byte[] receiveBuffer = new byte[1024];

            while(true) {

                Arrays.fill(receiveBuffer, (byte)0);
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                socket.receive(receivePacket);

                String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());


                //System.out.println("received udp:  from " + receivePacket.getAddress()+ " "+ receivePacket.getPort());
                System.out.println(msg);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (socket != null) {
                socket.close();
            }
        }


    }
}
