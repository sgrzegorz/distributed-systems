package tutorial;


import gen.User;
import gen.User.APIResponse;
import gen.userGrpc;
import io.grpc.stub.StreamObserver;


public class UserService extends userGrpc.userImplBase {
    @Override
    public void login(User.LoginRequest request, StreamObserver<User.APIResponse> responseObserver) {
        System.out.println("Inside login");

        String username = request.getUsername();
        String password =request.getPassword();

        APIResponse.Builder response = APIResponse.newBuilder();
        if(username.equals(password)){
            response.setResponseCode(0).setResponsemessage("SUCCESS");
        }else{
            response.setResponseCode(100).setResponsemessage("INVALID PASS");
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void logout(User.Empty request, StreamObserver<User.APIResponse> responseObserver) {

    }
}
