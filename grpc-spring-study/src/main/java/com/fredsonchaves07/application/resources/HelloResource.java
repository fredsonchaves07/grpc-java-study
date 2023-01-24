package com.fredsonchaves07.application.resources;

import com.fredsonchaves07.HelloReq;
import com.fredsonchaves07.HelloRes;
import com.fredsonchaves07.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloResource extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloReq request, StreamObserver<HelloRes> responseObserver) {
        var response = HelloRes.newBuilder().setMessage(request.getMessage()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
