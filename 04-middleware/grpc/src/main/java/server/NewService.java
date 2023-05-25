package server;


import gen.Weather.Data;
import gen.Weather.HelloRequest;
import gen.WeatherServiceGrpc.WeatherServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class NewService extends WeatherServiceImplBase implements Runnable {

    public BlockingQueue<Data> windQueue;
    private final static HashSet<StreamObserver<Data>> windObservers = new LinkedHashSet<>();

    public BlockingQueue<Data> stormQueue;
    private final static HashSet<StreamObserver<Data>> stormObservers = new LinkedHashSet<>();

    public BlockingQueue<Data> vulcQueue;
    private final static HashSet<StreamObserver<Data>> vulcObservers = new LinkedHashSet<>();

    public NewService() {
        windQueue = new ArrayBlockingQueue<>(1000);
        stormQueue = new ArrayBlockingQueue<>(1000);
        vulcQueue = new ArrayBlockingQueue<>(1000);
    }

    @Override
    public void storm(HelloRequest request, StreamObserver<Data> responseObserver) {
        stormObservers.add(responseObserver);
        System.out.println("Added storm observer");
//        super.storm(request, responseObserver);
//
//
//        Data.Builder response = Data.newBuilder();
//        response.setStrength("high").setWhatToDo("run away").setPlace("Carribean Sea");
//
//        responseObserver.onNext(response.build());
//        responseObserver.onCompleted();

    }

    @Override
    public void wind(HelloRequest request, StreamObserver<Data> responseObserver) {
        System.out.println("Added wind observer");
        windObservers.add(responseObserver);
    }

    @Override
    public void volcano(HelloRequest request, StreamObserver<Data> responseObserver) {
        System.out.println("Added volcano observer");
        vulcObservers.add(responseObserver);
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            try {

                sendToObservers(windQueue,windObservers);
                sendToObservers(stormQueue,stormObservers);
                sendToObservers(vulcQueue,vulcObservers);

                Thread.sleep(100);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Finish work");
    }

    public void sendToObservers(BlockingQueue<Data> queue,HashSet<StreamObserver<Data>> observers) {
        Data data = queue.poll();
        if (data != null) {

            if (data != null){
                for (Iterator<StreamObserver<Data>> iterator = observers.iterator(); iterator.hasNext(); ) {
                    StreamObserver<Data> streamObserver = iterator.next();
                    try {
                        streamObserver.onNext(data); //this line is grpc not iterator
                    } catch (Exception e) {
                        System.out.println("Unused observer deleted");
                        iterator.remove();
                    }
                }
            }
        }


    }

}
