package gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
//@javax.annotation.Generated(
//    value = "by gRPC proto compiler (version 1.15.0)",
//    comments = "Source: weather.proto")
public final class WeatherServiceGrpc {

  private WeatherServiceGrpc() {}

  public static final String SERVICE_NAME = "WeatherService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<gen.Weather.HelloRequest,
      gen.Weather.Data> getStormMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "storm",
      requestType = gen.Weather.HelloRequest.class,
      responseType = gen.Weather.Data.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<gen.Weather.HelloRequest,
      gen.Weather.Data> getStormMethod() {
    io.grpc.MethodDescriptor<gen.Weather.HelloRequest, gen.Weather.Data> getStormMethod;
    if ((getStormMethod = WeatherServiceGrpc.getStormMethod) == null) {
      synchronized (WeatherServiceGrpc.class) {
        if ((getStormMethod = WeatherServiceGrpc.getStormMethod) == null) {
          WeatherServiceGrpc.getStormMethod = getStormMethod = 
              io.grpc.MethodDescriptor.<gen.Weather.HelloRequest, gen.Weather.Data>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "WeatherService", "storm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.Weather.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.Weather.Data.getDefaultInstance()))
                  .setSchemaDescriptor(new WeatherServiceMethodDescriptorSupplier("storm"))
                  .build();
          }
        }
     }
     return getStormMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gen.Weather.HelloRequest,
      gen.Weather.Data> getWindMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "wind",
      requestType = gen.Weather.HelloRequest.class,
      responseType = gen.Weather.Data.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<gen.Weather.HelloRequest,
      gen.Weather.Data> getWindMethod() {
    io.grpc.MethodDescriptor<gen.Weather.HelloRequest, gen.Weather.Data> getWindMethod;
    if ((getWindMethod = WeatherServiceGrpc.getWindMethod) == null) {
      synchronized (WeatherServiceGrpc.class) {
        if ((getWindMethod = WeatherServiceGrpc.getWindMethod) == null) {
          WeatherServiceGrpc.getWindMethod = getWindMethod = 
              io.grpc.MethodDescriptor.<gen.Weather.HelloRequest, gen.Weather.Data>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "WeatherService", "wind"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.Weather.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.Weather.Data.getDefaultInstance()))
                  .setSchemaDescriptor(new WeatherServiceMethodDescriptorSupplier("wind"))
                  .build();
          }
        }
     }
     return getWindMethod;
  }

  private static volatile io.grpc.MethodDescriptor<gen.Weather.HelloRequest,
      gen.Weather.Data> getVolcanoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "volcano",
      requestType = gen.Weather.HelloRequest.class,
      responseType = gen.Weather.Data.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<gen.Weather.HelloRequest,
      gen.Weather.Data> getVolcanoMethod() {
    io.grpc.MethodDescriptor<gen.Weather.HelloRequest, gen.Weather.Data> getVolcanoMethod;
    if ((getVolcanoMethod = WeatherServiceGrpc.getVolcanoMethod) == null) {
      synchronized (WeatherServiceGrpc.class) {
        if ((getVolcanoMethod = WeatherServiceGrpc.getVolcanoMethod) == null) {
          WeatherServiceGrpc.getVolcanoMethod = getVolcanoMethod = 
              io.grpc.MethodDescriptor.<gen.Weather.HelloRequest, gen.Weather.Data>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "WeatherService", "volcano"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.Weather.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  gen.Weather.Data.getDefaultInstance()))
                  .setSchemaDescriptor(new WeatherServiceMethodDescriptorSupplier("volcano"))
                  .build();
          }
        }
     }
     return getVolcanoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WeatherServiceStub newStub(io.grpc.Channel channel) {
    return new WeatherServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WeatherServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new WeatherServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WeatherServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new WeatherServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class WeatherServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void storm(gen.Weather.HelloRequest request,
        io.grpc.stub.StreamObserver<gen.Weather.Data> responseObserver) {
      asyncUnimplementedUnaryCall(getStormMethod(), responseObserver);
    }

    /**
     */
    public void wind(gen.Weather.HelloRequest request,
        io.grpc.stub.StreamObserver<gen.Weather.Data> responseObserver) {
      asyncUnimplementedUnaryCall(getWindMethod(), responseObserver);
    }

    /**
     */
    public void volcano(gen.Weather.HelloRequest request,
        io.grpc.stub.StreamObserver<gen.Weather.Data> responseObserver) {
      asyncUnimplementedUnaryCall(getVolcanoMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStormMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                gen.Weather.HelloRequest,
                gen.Weather.Data>(
                  this, METHODID_STORM)))
          .addMethod(
            getWindMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                gen.Weather.HelloRequest,
                gen.Weather.Data>(
                  this, METHODID_WIND)))
          .addMethod(
            getVolcanoMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                gen.Weather.HelloRequest,
                gen.Weather.Data>(
                  this, METHODID_VOLCANO)))
          .build();
    }
  }

  /**
   */
  public static final class WeatherServiceStub extends io.grpc.stub.AbstractStub<WeatherServiceStub> {
    private WeatherServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WeatherServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WeatherServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WeatherServiceStub(channel, callOptions);
    }

    /**
     */
    public void storm(gen.Weather.HelloRequest request,
        io.grpc.stub.StreamObserver<gen.Weather.Data> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getStormMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void wind(gen.Weather.HelloRequest request,
        io.grpc.stub.StreamObserver<gen.Weather.Data> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getWindMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void volcano(gen.Weather.HelloRequest request,
        io.grpc.stub.StreamObserver<gen.Weather.Data> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getVolcanoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class WeatherServiceBlockingStub extends io.grpc.stub.AbstractStub<WeatherServiceBlockingStub> {
    private WeatherServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WeatherServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WeatherServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WeatherServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<gen.Weather.Data> storm(
        gen.Weather.HelloRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getStormMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<gen.Weather.Data> wind(
        gen.Weather.HelloRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getWindMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<gen.Weather.Data> volcano(
        gen.Weather.HelloRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getVolcanoMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class WeatherServiceFutureStub extends io.grpc.stub.AbstractStub<WeatherServiceFutureStub> {
    private WeatherServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WeatherServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WeatherServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WeatherServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_STORM = 0;
  private static final int METHODID_WIND = 1;
  private static final int METHODID_VOLCANO = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final WeatherServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(WeatherServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STORM:
          serviceImpl.storm((gen.Weather.HelloRequest) request,
              (io.grpc.stub.StreamObserver<gen.Weather.Data>) responseObserver);
          break;
        case METHODID_WIND:
          serviceImpl.wind((gen.Weather.HelloRequest) request,
              (io.grpc.stub.StreamObserver<gen.Weather.Data>) responseObserver);
          break;
        case METHODID_VOLCANO:
          serviceImpl.volcano((gen.Weather.HelloRequest) request,
              (io.grpc.stub.StreamObserver<gen.Weather.Data>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class WeatherServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WeatherServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return gen.Weather.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("WeatherService");
    }
  }

  private static final class WeatherServiceFileDescriptorSupplier
      extends WeatherServiceBaseDescriptorSupplier {
    WeatherServiceFileDescriptorSupplier() {}
  }

  private static final class WeatherServiceMethodDescriptorSupplier
      extends WeatherServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    WeatherServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (WeatherServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WeatherServiceFileDescriptorSupplier())
              .addMethod(getStormMethod())
              .addMethod(getWindMethod())
              .addMethod(getVolcanoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
