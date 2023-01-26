package com.fredsonchaves07.application.grpc;

import com.fredsonchaves07.ProductRequest;
import com.fredsonchaves07.ProductResponse;
import com.fredsonchaves07.ProductServiceGrpc;
import com.fredsonchaves07.RequestById;
import com.fredsonchaves07.application.exception.NotFoundException;
import com.fredsonchaves07.domain.repository.ProductRepository;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.assertj.core.api.Assertions;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductGrpcIntegrationTest {

    @GrpcClient("inProcess")
    private ProductServiceGrpc.ProductServiceBlockingStub serviceBlockingStub;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    private void setUp() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void createProductSucessTest() {
        ProductRequest productRequest = ProductRequest.newBuilder()
                .setName("Product 1")
                .setPrice(10)
                .setQuantityInStock(100)
                .build();
        ProductResponse productResponse = serviceBlockingStub.create(productRequest);
        Assertions.assertThat(productRequest)
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "price", "quantity_in_stock")
                .isEqualTo(productResponse);
    }

    @Test
    public void createProductExceptionTest() {
        ProductRequest productRequest = ProductRequest.newBuilder()
                .setName("Product B")
                .setPrice(25.6)
                .setQuantityInStock(10)
                .build();
        Assertions.assertThatExceptionOfType(StatusRuntimeException.class)
                .isThrownBy(() -> serviceBlockingStub.create(productRequest))
                .withMessage("ALREADY_EXISTS: Produto Product B cadastrado no sistema");
    }

    @Test
    public void findByIdProductSucessTest() {
        RequestById request = RequestById.newBuilder()
                .setId(100L).build();
        ProductResponse productResponse = serviceBlockingStub.findById(request);
        Assertions.assertThat(productResponse.getId()).isEqualTo(request.getId());
    }

    @Test
    public void findByIdProductExceptionTest() {
        RequestById request = RequestById.newBuilder()
                .setId(1L).build();
        Assertions.assertThatExceptionOfType(StatusRuntimeException.class)
                .isThrownBy(() -> serviceBlockingStub.findById(request))
                .withMessage("NOT_FOUND: Produto com id 1 nao encontrado");
    }
}
