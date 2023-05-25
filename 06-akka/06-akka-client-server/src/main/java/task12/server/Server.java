package task12.server;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;


public class Server {



    public static void main(String[] args) throws Exception {

        // config
        File configFile = new File("server_remote.conf");
        Config config = ConfigFactory.parseFile(configFile);
        
        // create actor system & actors
        final ActorSystem system = ActorSystem.create("remote_system", config);
        final ActorRef remote = system.actorOf(Props.create(ReceptionistActor.class), "receptionist");
        final ActorRef database = system.actorOf(Props.create(DatabaseActor.class), "database");

        // interaction
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line.equals("q")) {
                break;
            }
        }

        system.terminate();
    }
}