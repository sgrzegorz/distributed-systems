package server;


import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;


public class NewServer {
    public static void main(String[] args) throws InterruptedException, IOException {

        NewService newService = new NewService();
        Thread t = new Thread(newService);
        t.start();

        ServerConsole serverConsole = new ServerConsole(newService);
        serverConsole.start();


        Server server = ServerBuilder.forPort(9090).addService(newService).build();
        server.start();
        System.out.println("Server started at " + server.getPort());
        server.awaitTermination();


    }
}
