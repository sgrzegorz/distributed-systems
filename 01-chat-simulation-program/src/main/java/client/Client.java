package client;



import multicast.UdpMulticastListener;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Client {

    PrintWriter out;
    BufferedReader in;
    DatagramSocket udpSocket = null;
    int newServerPort;
    int id;
    UdpBroadcastListener udpBroadcastListener;
    UdpMulticastListener udpMulticastListener;
    TcpListener tcpListener;
    MulticastSocket multicastSocket;
    Scanner sc = new Scanner(System.in);

    public Client() throws Exception{


        try {

            this.newServerPort =initialiseConnectionToServer();
            this.id = newServerPort;
            System.out.println("CLIENT "+id +"\n------------------------------");


            Thread.sleep(500);


        } catch (Exception e) {
            throw new Exception(id+" Connecting to server failed... Server not found");

        }
    }

    public int initialiseConnectionToServer() throws Exception {

        Socket tcpSocket = new Socket("localhost", 12345);
        PrintWriter out = new PrintWriter(tcpSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
        out.println("A Hej   co tam :) ");
        newServerPort = Integer.parseInt(in.readLine());

        tcpSocket.close();

        return newServerPort;
    }

    public void doClientTask() throws Exception {


        // initialise new tcp connection
        String hostName ="localhost";
        Socket newTcpSocket =  new Socket(hostName, newServerPort);

        out = new PrintWriter(newTcpSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(newTcpSocket.getInputStream()));

        //initialise udp
        InetAddress ip = InetAddress.getByName("localhost");
        udpSocket = new DatagramSocket(newTcpSocket.getLocalPort(),ip);

        //multicast socket
        InetAddress group = InetAddress.getByName("228.5.6.7");
        multicastSocket = new MulticastSocket(6789);
        multicastSocket.joinGroup(group);




        udpBroadcastListener = new UdpBroadcastListener(udpSocket);
        udpMulticastListener = new UdpMulticastListener(multicastSocket);
        tcpListener = new TcpListener(in);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        executor.submit(udpBroadcastListener);
        executor.submit(udpMulticastListener);
        executor.submit(tcpListener);




        while (true) {

            handleInputLine();

        }

    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.doClientTask();

    }


    public void handleInputLine() throws Exception{
        String help = "\n[HOW TO USE]\n" +
                "A <message>\t send message to everyone online\n" +
                "U <path_to_ascii_art>\t send ascii art to everyone online \n" +
                "M <message>\t send ascii art to everyone online by multicast\n" +
                "\n" +
                "[EXAMPLES]\n" +
                "A Hello everyone!\n" +
                "U ascii.txt \n" +
                "M ascii.txt";

        help ="wrong_input";

        while(sc.hasNextLine()) {


            String command = sc.nextLine();
            String[] arr = command.split(" ", 2);

            String[] tokens = StringUtils.split(command);
            if(tokens!=null && tokens.length>=1) {
                if(tokens[0].equals("A")){
                    out.println(arr[1]);
                }else if(tokens[0].equals("U")){
                    if(tokens.length==2){
                        try{
                            String ascii = readAsciiFromFile(tokens[1], StandardCharsets.UTF_8);
                            sendUdp(id+"> "+ascii);
                        }catch (NoSuchFileException e){
                            System.out.println("No file with given name");
                        }
                    }else if(tokens.length==1){
                        String ascii = readAsciiFromFile("ascii.txt", StandardCharsets.UTF_8);
                        sendUdp(id+"> \n"+ascii);
                    }else{
                        System.out.println(help+"1");
                    }

                }else if(tokens[0].equals("M")){
                    if(tokens.length==1){
                        String ascii = readAsciiFromFile("ascii.txt", StandardCharsets.UTF_8);
                        String msg = id+">\n"+ascii;
                        InetAddress group = InetAddress.getByName("228.5.6.7");
                        DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(), group, 6789);
                        multicastSocket.send(dp);
                    }else if(tokens.length==2){
                        try{
                            String ascii = readAsciiFromFile(tokens[1], StandardCharsets.UTF_8);
                            String msg = id+">\n"+ascii;
                            InetAddress group = InetAddress.getByName("228.5.6.7");
                            DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(), group, 6789);
                            multicastSocket.send(dp);

                        }catch(NoSuchFileException e){
                            System.out.println("No such file found");
                        }

                    }else{
                        String msg =id+">"+arr[1];
                        InetAddress group = InetAddress.getByName("228.5.6.7");
                        DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(), group, 6789);
                        multicastSocket.send(dp);
                    }


                }else{
                    System.out.println(help+"2");
                }

            }else{
                System.out.println(help+"3");
            }


        }

    }



    private void sendUdp(String string) {
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            byte[] sendBuffer = string.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, ip, newServerPort);
            udpSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    String readAsciiFromFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }



}
