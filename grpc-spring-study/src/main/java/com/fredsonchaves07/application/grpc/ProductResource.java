package com.fredsonchaves07.application.grpc;

import com.fredsonchaves07.ProductRequest;
import com.fredsonchaves07.ProductResponse;
import com.fredsonchaves07.ProductServiceGrpc;
import com.fredsonchaves07.RequestById;
import com.fredsonchaves07.application.dto.ProductInputDTO;
import com.fredsonchaves07.application.dto.ProductOutputDTO;
import com.fredsonchaves07.domain.services.IProductService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class ProductResource extends ProductServiceGrpc.ProductServiceImplBase {

    @Autowired
    IProductService service;

    @Override
    public void create(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        ProductInputDTO productInputDTO = new ProductInputDTO(
                request.getName(), request.getPrice(), request.getQuantityInStock()
        );
        ProductOutputDTO productOutputDTO = service.create(productInputDTO);
        ProductResponse response = ProductResponse.newBuilder()
                .setId(productOutputDTO.id())
                .setName(productOutputDTO.name())
                .setPrice(productOutputDTO.price())
                .setQuantityInStock(productOutputDTO.quantityInStock()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findById(RequestById request, StreamObserver<ProductResponse> responseObserver) {
        ProductOutputDTO productOutputDTO = service.findById(request.getId());
        ProductResponse response = ProductResponse.newBuilder()
                .setId(productOutputDTO.id())
                .setName(productOutputDTO.name())
                .setPrice(productOutputDTO.price())
                .setQuantityInStock(productOutputDTO.quantityInStock()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
