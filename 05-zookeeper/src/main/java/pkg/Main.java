package pkg;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        if (args.length == 0) {
            ExternalProgramRunner.path = "C:\\WINDOWS\\system32\\mspaint.exe";
        } else if (args.length == 1) {
            ExternalProgramRunner.path = args[0];
        } else {
            System.out.print("Type path to executable. If no argument given - default program is Paint.");
        }

        NodeWatcher nodeWatcher = new NodeWatcher("127.0.0.1:2171");


        Scanner scanner = new Scanner(System.in);
        while (true) {
            scanner.next();
            nodeWatcher.printTree("/z", 0);
        }

    }

}
