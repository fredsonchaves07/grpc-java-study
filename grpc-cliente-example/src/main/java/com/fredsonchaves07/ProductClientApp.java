package com.fredsonchaves07;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ProductClientApp {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        ProductServiceGrpc.ProductServiceBlockingStub stub =
                ProductServiceGrpc.newBlockingStub(channel);
        ProductRequest productRequest = ProductRequest.newBuilder()
                .setName("Product 1")
                .setPrice(10)
                .setQuantityInStock(100)
                .build();
        ProductResponse productResponse = stub.create(productRequest);
        channel.shutdown();
    }
}
