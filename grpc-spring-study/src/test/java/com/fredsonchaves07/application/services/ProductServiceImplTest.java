package com.fredsonchaves07.application.services;

import com.fredsonchaves07.application.dto.ProductInputDTO;
import com.fredsonchaves07.application.dto.ProductOutputDTO;
import com.fredsonchaves07.application.exception.AlreadyExistsException;
import com.fredsonchaves07.application.exception.NotFoundException;
import com.fredsonchaves07.application.services.ProductServiceImpl;
import com.fredsonchaves07.domain.entity.Product;
import com.fredsonchaves07.domain.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.tuple;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    @Test
    @DisplayName("when create product service is call with valid data a product is returned")
    public void createProductSucessTest() {
        Product product = new Product(1L, "product name", 10.0, 10);
        Mockito.when(repository.save(Mockito.any())).thenReturn(product);
        ProductInputDTO productInputDTO = new ProductInputDTO("product name", 10.0, 10);
        ProductOutputDTO productOutputDTO = service.create(productInputDTO);
        Assertions.assertThat(productOutputDTO)
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "price", "quantity_in_stock")
                .isEqualTo(product);
    }

    @Test
    public void createProductException() {
        Product product = new Product(1L, "Product test", 10.99, 10);
        Mockito.when(repository.findByNameIgnoreCase(Mockito.any())).thenReturn(Optional.of(product));
        ProductInputDTO productInputDTO = new ProductInputDTO("Product test", 10.99, 10);
        Assertions.assertThatExceptionOfType(AlreadyExistsException.class)
                        .isThrownBy(() -> service.create(productInputDTO));
    }

    @Test
    public void findByIdProductSucess() {
        Long id = 1L;
        Product product = new Product(1L, "Product test", 10.99, 10);
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(product));
        ProductOutputDTO productOutputDTO = service.findById(id);
        Assertions.assertThat(productOutputDTO)
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "price", "quantity_in_stock")
                .isEqualTo(product);
    }

    @Test
    public void findByIdProductException() {
        Long id = 100L;
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> service.findById(id));
    }

    @Test
    public void deleteProductSucess() {
        Long id = 1L;
        Product product = new Product(1L, "Product test", 10.99, 10);
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(product));
        Assertions.assertThatNoException().isThrownBy(() -> service.delete(id));
    }

    @Test
    public void deleteProductException() {
        Long id = 100L;
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> service.delete(id));
    }

    @Test
    public void getAllSucessProduct() {
        List<Product> products = List.of(
                new Product(1L, "Product test", 10.99, 10),
                new Product(2L, "Product 1", 10.99, 10),
                new Product(3L, "Product 2", 10.99, 10)
        );
        Mockito.when(repository.findAll()).thenReturn(products);
        List<ProductOutputDTO> outputDTOS = service.findAll();
        Assertions.assertThat(outputDTOS)
                .extracting("id", "name", "price", "quantityInStock")
                .contains(
                        tuple(1L, "Product test", 10.99, 10),
                        tuple(2L, "Product 1", 10.99, 10),
                        tuple(3L, "Product 2", 10.99, 10)
                );
    }
}
