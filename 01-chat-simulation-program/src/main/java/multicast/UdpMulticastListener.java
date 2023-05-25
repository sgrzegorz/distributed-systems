package multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UdpMulticastListener extends Thread{
    MulticastSocket multicastSocket;

    public UdpMulticastListener(MulticastSocket multicastSocket) {
        this.multicastSocket = multicastSocket;
    }


    @Override
    public void run() {
        try {


            byte[] buf = new byte[1000];
            while(true){
                DatagramPacket recv = new DatagramPacket(buf, buf.length);
                multicastSocket.receive(recv);
                String msg1 = new String(recv.getData(), 0, recv.getLength());
                System.out.println(msg1);

            }
//            multicastSocket.leaveGroup(group);



        } catch (Exception e) {

        }
    }

}