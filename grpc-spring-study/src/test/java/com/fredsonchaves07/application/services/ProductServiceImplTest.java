package com.fredsonchaves07.application.services;

import com.fredsonchaves07.application.dto.ProductInputDTO;
import com.fredsonchaves07.application.dto.ProductOutputDTO;
import com.fredsonchaves07.application.exception.AlreadyExistsException;
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

import java.util.Optional;

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
}
