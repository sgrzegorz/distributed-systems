package server;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class ServerWorker extends Thread {

    boolean verbose = false;
    int id;

    Action action = Action.DO_NOTHING;
    LinkedList<Task> tasksFIFO = new LinkedList<>();
    private ServerSocket tcpSocket =null;
    Socket clientSocket =null;
    private Server server;
    int newServerPort;
    PrintWriter out;
    BufferedReader in;
    ThreadController threadController;

    DatagramSocket udpSocket = null;


    public ServerWorker(Server server, int newServerPort,DatagramSocket udpSocket, ThreadController threadController) {
        this.server = server;
        this.id = newServerPort;
        this.newServerPort =newServerPort;
        this.threadController = threadController;
        threadController.addServerWorker(this);

        this.udpSocket = udpSocket;

    }

    @Override
    public void run() {
        try {

            doServerWorkerTask();

            if(tcpSocket!=null) tcpSocket.close();
            if (udpSocket != null) udpSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println(id + " closed connection");

        }
    }

    private void doServerWorkerTask() throws Exception {
        if(verbose) System.out.println(id + " starting doServerWorkerTask ");

        //initialise tcp
        if(verbose) System.out.println(id+" tcp connection "+ newServerPort);
        tcpSocket = new ServerSocket(newServerPort);
        clientSocket = tcpSocket.accept();
        System.out.println(id+ " connection accepted");
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));






        while (true) {
            if(verbose)System.out.println(id +"< handle task fifo");
            while (tasksFIFO.size() != 0) {
                Task task = tasksFIFO.removeFirst();


                switch (task.action){
                    case SEND_TO_ALL:
                        System.out.println(id + " Handle tcp |" + task.getMsg());
                        out.println(task.getMsg());

                        break;

                    case SEND_UDP:
                        System.out.println(id + " Handle udp:\n " + task.getMsg());
                        sendUdp(task.getMsg());
                        break;
                    default:
                        System.out.println(id + " handling unknown action");

                }
            }
            if(verbose)System.out.println("> handle task fifo finished");


            if(verbose)System.out.println("! " + in.ready());

            String line;
            if (in.ready()) {
                if(verbose) System.out.println("?");

                if ((line = in.readLine()) != null) {
                    String msg = newServerPort+"> "+line;
                    Task task = new Task(Action.SEND_TO_ALL, msg,newServerPort);
                    System.out.println(id + " Received tcp message: "+msg);
                    threadController.broadcast(task, this);
                }
            }


            Thread.sleep(10);
            if(verbose) System.out.println(id + " loops");
        }
    }



    public void addNewTask(Task task) {
        tasksFIFO.add(task);
    }


    private void sendUdp(String string){
        try {
            InetAddress address = InetAddress.getByName("localhost");

            byte[] sendBuffer = string.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, clientSocket.getPort());
            udpSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
