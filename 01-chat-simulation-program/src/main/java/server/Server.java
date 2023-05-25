package server;

import multicast.UdpMulticastListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server extends Thread{
    private final int serverPort;
    private final String serverHostName;
    ServerSocket serverTcpSocket = null;
    ThreadController threadController;


    public Server(int portNumber, String serverHostName) {
        this.serverPort = portNumber;
        this.serverHostName = serverHostName;
    }


    int maxClients =10;
    int newServerPort =12346;


    
    public static void main(String[] args) {

        int portNumber = 12345;
        String serverHostName ="local";
        Server server = new Server(portNumber, serverHostName);
        server.start();

        if(false){
            try {
                InetAddress group = InetAddress.getByName("228.5.6.7");
                MulticastSocket multicastSocket = new MulticastSocket(6789);

                multicastSocket.joinGroup(group);
                UdpMulticastListener udpMulticastListener = new UdpMulticastListener(multicastSocket);
                udpMulticastListener.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void run(){



        try {
            ThreadController threadController = new ThreadController();

            serverTcpSocket = new ServerSocket(serverPort);



            ThreadPoolExecutor serverWorkerExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(maxClients);
            ThreadPoolExecutor udpListenerExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(maxClients);


            while(true){

                System.out.println("SERVER is about to accept client connection");
                Socket clientTcpSocket = serverTcpSocket.accept();
                System.out.println("SERVER accepted connection from "+ clientTcpSocket + "added it id="+newServerPort);


                PrintWriter out = new PrintWriter(clientTcpSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientTcpSocket.getInputStream()));
                out.println(newServerPort);



                InetAddress ip = InetAddress.getByName("localhost");
                DatagramSocket udpSocket = new DatagramSocket(newServerPort,ip);

                ServerWorker serverWorker =  new ServerWorker(this,newServerPort,udpSocket,threadController);
                serverWorkerExecutor.submit(serverWorker);
                udpListenerExecutor.submit(new UdpListener(newServerPort,this,serverWorker,udpSocket,threadController));
                newServerPort++;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if (serverTcpSocket != null){
                try {
                    serverTcpSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }








}
