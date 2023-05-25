package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UdpListener extends Thread {
    int newServerPort;
    int id;
    Server server;
    ServerWorker serverWorker;
    DatagramSocket udpSocket = null;
    ThreadController threadController;




    public UdpListener(int newServerPort, Server server,ServerWorker serverWorker,DatagramSocket udpSocket, ThreadController threadController){
        this.id = newServerPort*(-1);
        this.newServerPort = newServerPort;
        this.server = server;
        this.serverWorker = serverWorker;
        this.udpSocket = udpSocket;
        this.threadController = threadController;
        

    }

    @Override
    public void run() {
        System.out.println(id+" starts working");



        try{

            byte[] receiveBuffer = new byte[1024];

            while(true) {

                Arrays.fill(receiveBuffer, (byte)0);
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                udpSocket.receive(receivePacket);
                String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println(id +" received msg: " + msg+ " from " + receivePacket.getAddress()+ " "+ receivePacket.getPort());

                Task task = new Task(Action.SEND_UDP,msg,newServerPort);
                threadController.broadcast(task,serverWorker);


            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (udpSocket != null) {
                udpSocket.close();
            }
        }


    }


}
