package pkg;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;

import java.security.cert.PolicyNode;
import java.util.List;

public class ChildrenWatcher implements Watcher {

    public ChildrenWatcher(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    private ZooKeeper zooKeeper;

    @Override
    public void process(WatchedEvent watchedEvent) { //
        try {
            List<String> children = zooKeeper.getChildren(watchedEvent.getPath(), this); //ustawiam watcher, który wlasnie wypalil

            for (String child : children) {
                zooKeeper.getChildren(watchedEvent.getPath() + "/" + child, this); //Nie wiem ktory child zostal dodany wiec ustawiam watcher na wszystkich
            }


            System.out.println("Number of descendants: " + getNumberOfDescendant("/z"));  //jesli child zostal usuniety a nie dodany to wczesniej wyrzuci KeeperException


        } catch (KeeperException e) {
            if (e.code() == KeeperException.Code.NONODE) {
                System.out.println("Node deleted");
            } else {

                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int getNumberOfDescendant(String nodeName) {
        return getNumberOfDescendantPlusOne(nodeName) - 1;
    }

    private int getNumberOfDescendantPlusOne(String nodeName) {
        int number = 1;
        try {
            List<String> children = zooKeeper.getChildren(nodeName, false);

            for (String child : children) {
                number += getNumberOfDescendantPlusOne(nodeName + "/" + child);
            }
            return number;
        } catch (KeeperException | InterruptedException e) {
            System.out.println("Node deletion");
//            e.printStackTrace();
        }
        return number;
    }

}
