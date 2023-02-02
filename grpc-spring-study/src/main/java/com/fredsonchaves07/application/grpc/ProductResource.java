package com.fredsonchaves07.application.grpc;

import com.fredsonchaves07.*;
import com.fredsonchaves07.application.dto.ProductInputDTO;
import com.fredsonchaves07.application.dto.ProductOutputDTO;
import com.fredsonchaves07.domain.services.IProductService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    @Override
    public void delete(RequestById request, StreamObserver<EmptyResponse> responseObserver) {
        service.delete(request.getId());
        responseObserver.onNext(EmptyResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void findAll(EmptyRequest request, StreamObserver<ProductResponseList> responseObserver) {
        List<ProductOutputDTO> outputDTOS = service.findAll();
        List<ProductResponse> productResponses = outputDTOS.stream()
                .map(product ->
                        ProductResponse.newBuilder()
                                .setId(product.id())
                                .setName(product.name())
                                .setPrice(product.price())
                                .setQuantityInStock(product.quantityInStock()).build())
                .toList();
        ProductResponseList build = ProductResponseList.newBuilder()
                .addAllProducts(productResponses).build();
        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }
}
