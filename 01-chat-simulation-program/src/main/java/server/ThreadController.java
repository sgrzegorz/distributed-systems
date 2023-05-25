package server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadController {
    final Lock lock = new ReentrantLock();
    private ArrayList<ServerWorker> workerList = new ArrayList<>();

    public void broadcast(Task task,ServerWorker sw){
        lock.lock();
        try{
            for(ServerWorker serverWorker: workerList){
                if(serverWorker !=sw){
                serverWorker.addNewTask(task);
                }
            }

        }finally {
            lock.unlock();
        }
    }

    public void addServerWorker(ServerWorker serverWorker){
        try {
            lock.lock();

            workerList.add(serverWorker);


        }finally {
            lock.unlock();
        }
    }

    public void removeServerWorker(ServerWorker serverWorker) throws Exception{
        boolean errorChecker = false;
        for(ServerWorker sw : workerList){
            if(sw == serverWorker ){
                if(errorChecker) throw new Exception("One worker was added multiple times to workerList");
                workerList.remove(sw);
                errorChecker = true;
            }
        }
    }

    public List<ServerWorker> getWorkerList(){
        return workerList;
    }


}
