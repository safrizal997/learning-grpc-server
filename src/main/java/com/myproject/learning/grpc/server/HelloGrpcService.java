package com.myproject.learning.grpc.server;

import com.myproject.learning.grpc.server.proto.GetUserDetailRequest;
import com.myproject.learning.grpc.server.proto.GetUserDetailResponse;
import com.myproject.learning.grpc.server.proto.HelloRequest;
import com.myproject.learning.grpc.server.proto.HelloResponse;
import com.myproject.learning.grpc.server.proto.HelloServiceGrpc;
import com.myproject.learning.grpc.server.service.UserService;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class HelloGrpcService extends HelloServiceGrpc.HelloServiceImplBase {

    private final UserService userService;

    public HelloGrpcService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse response = HelloResponse.newBuilder()
                .setMessage("Hello, " + request.getName() + "!")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUserDetail(GetUserDetailRequest request, StreamObserver<GetUserDetailResponse> responseObserver) {
        userService.getUserDetail(request.getUserId())
                .map(user -> GetUserDetailResponse.newBuilder()
                        .setUserId(user.getUserId())
                        .setName(user.getName())
                        .setEmail(user.getEmail())
                        .setAge(user.getAge())
                        .setPhone(user.getPhone())
                        .build())
                .ifPresentOrElse(
                        response -> {
                            responseObserver.onNext(response);
                            responseObserver.onCompleted();
                        },
                        () -> responseObserver.onError(
                                Status.NOT_FOUND
                                        .withDescription("User not found: " + request.getUserId())
                                        .asRuntimeException()
                        )
                );
    }
}
