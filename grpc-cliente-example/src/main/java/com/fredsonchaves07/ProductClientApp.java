package com.fredsonchaves07;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ProductClientApp {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();
    }
}
