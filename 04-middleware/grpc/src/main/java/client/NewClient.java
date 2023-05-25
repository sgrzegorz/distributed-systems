package client;


import gen.Weather.Data;
import gen.Weather.HelloRequest;
import gen.WeatherServiceGrpc;
import gen.WeatherServiceGrpc.WeatherServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class NewClient extends Thread{
    String args[];

    public NewClient(String[] args) {
        this.args = args;
    }

    public void run() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();

        WeatherServiceStub windStub = WeatherServiceGrpc.newStub(channel);
        WeatherServiceStub stormStub = WeatherServiceGrpc.newStub(channel);
        WeatherServiceStub vulcStub = WeatherServiceGrpc.newStub(channel);

        selectTasks(windStub,stormStub,vulcStub);

        while(true){

//            System.out.println("loop");
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
        }
    }

    public void selectTasks(WeatherServiceStub windStub,WeatherServiceStub stormStub,WeatherServiceStub vulcStub){
        for(String task : args){
            if(task.equals("vulc")){
                subscribeVulc(vulcStub);
            }else if(task.equals("wind")){
                subscribeWind(windStub);
            }else if(task.equals("storm")){
                subscribeStorm(stormStub);
            }else{
                System.out.println("Unknown arg");
                System.exit(1);
            }
        }
        System.out.println("Tasks subscribed");
    }

    private void subscribeWind(WeatherServiceStub stub){
        HelloRequest helloRequest = HelloRequest.newBuilder().setGreeting("hey I subscribe").build();

        stub.wind(helloRequest,new StreamObserver<Data>(){
            @Override
            public void onNext(Data value) {
                System.out.println("got WIND message: " +value.getStrength()+" "+value.getWhatToDo()+" "+value.getPlace());
            }

            @Override
            public void onError(Throwable t) {
//                System.out.println("ERROR");
//                    t.printStackTrace();
//                    throw new Exception("Exception");
//                    Thread.currentThread().interrupt();
                ClientOverseer.recovery=true;
            }

            @Override
            public void onCompleted() {
                System.out.println("On completed");
            }

        });
    }

    private void subscribeVulc(WeatherServiceStub stub){
        HelloRequest helloRequest = HelloRequest.newBuilder().setGreeting("hey I subscribe").build();

        stub.volcano(helloRequest,new StreamObserver<Data>(){
            @Override
            public void onNext(Data value) {
                System.out.println("got VULC message: " +value.getStrength()+" "+value.getWhatToDo()+" "+value.getPlace());
            }

            @Override
            public void onError(Throwable t) {
//                System.out.println("ERROR");
//                    t.printStackTrace();
//                    throw new Exception("Exception");
//                    Thread.currentThread().interrupt();
                ClientOverseer.recovery=true;
            }

            @Override
            public void onCompleted() {
                System.out.println("On completed");
            }

        });
    }

    private void subscribeStorm(WeatherServiceStub stub){
        HelloRequest helloRequest = HelloRequest.newBuilder().setGreeting("hey I subscribe").build();

        stub.storm(helloRequest,new StreamObserver<Data>(){
            @Override
            public void onNext(Data value) {
                System.out.println("got STORM message: " +value.getStrength()+" "+value.getWhatToDo()+" "+value.getPlace());
            }

            @Override
            public void onError(Throwable t) {
//                System.out.println("ERROR");
//                    t.printStackTrace();
//                    throw new Exception("Exception");
//                    Thread.currentThread().interrupt();
                ClientOverseer.recovery=true;
            }

            @Override
            public void onCompleted() {
                System.out.println("On completed");
            }

        });
    }
}
