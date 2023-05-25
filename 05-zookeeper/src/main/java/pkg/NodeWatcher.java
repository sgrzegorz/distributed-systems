package pkg;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

public class NodeWatcher implements Watcher {
    String znode = "/z";
    ZooKeeper zooKeeper;
    ChildrenWatcher childrenWatcher;

    public NodeWatcher(String configuration) {


        try {

            this.zooKeeper = new ZooKeeper(configuration, 300000, this);
            this.childrenWatcher = new ChildrenWatcher(this.zooKeeper);
            zooKeeper.exists(znode, true);
            registerChildrenWatcherForAllSubtree(znode);

        } catch (InterruptedException | IOException | KeeperException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            if (watchedEvent.getType() == EventType.NodeCreated) {
                System.out.println("Root created");
                ExternalProgramRunner.start();
                zooKeeper.exists(znode, this); //root was added so listen for events on it
                zooKeeper.getChildren(znode, childrenWatcher); //listen for events on its children on childrenWatcher

            } else if (watchedEvent.getType() == EventType.NodeDeleted) {
                System.out.println("Root deleted");
                ExternalProgramRunner.stop();
                zooKeeper.exists("/z", this);

            } else if (watchedEvent.getType() == EventType.NodeChildrenChanged) {
                System.out.println("ChildrenChanged");
            } else {

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void printTree(String nodeName, int stage) {

        try {
            printStage(stage);
            System.out.println(nodeName);

            List<String> children = zooKeeper.getChildren(nodeName, false);
            for (String child : children) {

                printTree(nodeName + "/" + child, stage + 1);
            }

        } catch (KeeperException e) {
            System.out.println("Node /z not found");
//            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printStage(int stage) {
        for (int i = 0; i < stage; i++) {
            System.out.print("\t");
        }
    }

    public void registerChildrenWatcherForAllSubtree(String nodeName) {

        try {
            List<String> children = zooKeeper.getChildren(nodeName, childrenWatcher);
            for (String child : children) {

                registerChildrenWatcherForAllSubtree(nodeName + "/" + child);
            }

        } catch (KeeperException e) {
            System.out.println("Node /z not found");
//            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
