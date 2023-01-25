package com.fredsonchaves07.application.grpc;

import com.fredsonchaves07.ProductRequest;
import com.fredsonchaves07.ProductResponse;
import com.fredsonchaves07.ProductServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductGrpcIntegrationTest {

    @GrpcClient("inProcess")
    private ProductServiceGrpc.ProductServiceBlockingStub serviceBlockingStub;

    @Test
    public void createProductSucessTest() {
        ProductRequest productRequest = ProductRequest.newBuilder()
                .setName("Product 1")
                .setPrice(10.0)
                .setQuantityInStock(100)
                .build();
        ProductResponse productResponse = serviceBlockingStub.create(productRequest);
        Assertions.assertThat(productRequest)
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "price", "quantity_in_stock")
                .isEqualTo(productResponse);
    }
}
