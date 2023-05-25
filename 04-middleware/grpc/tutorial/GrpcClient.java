package tutorial;


import gen.User.APIResponse;
import gen.User.LoginRequest;
import gen.userGrpc;
import gen.userGrpc.userBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String [] args){
        ManagedChannel channel =ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();

//        userGrpc.newBlockingStub()
        userGrpc.newBlockingStub(channel);

        userBlockingStub userStub = userGrpc.newBlockingStub(channel);

        LoginRequest loginRequest = LoginRequest.newBuilder().setUsername("RAM").setPassword("RAM1").build();
        APIResponse response = userStub.login(loginRequest);

        System.out.println(response.getResponsemessage());





    }
}
