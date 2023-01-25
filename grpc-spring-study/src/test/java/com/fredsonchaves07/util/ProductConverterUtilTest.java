package com.fredsonchaves07.util;

import com.fredsonchaves07.application.dto.ProductInputDTO;
import com.fredsonchaves07.application.dto.ProductOutputDTO;
import com.fredsonchaves07.domain.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductConverterUtilTest {

    @Test
    public void productToProductOutputDTOTest() {
        Product product = new Product(1L, "Product Name", 10.00, 10);
        ProductOutputDTO productOutputDTO = ProductConverterUtil.toProductOutputDTO(product);
        Assertions.assertThat(product)
                .usingRecursiveComparison()
                .isEqualTo(productOutputDTO);
    }

    @Test
    public void productInputToProductTest() {
        ProductInputDTO productInputDTO = new ProductInputDTO( "Product Name", 10.00, 10);
        Product product = ProductConverterUtil.toProduct(productInputDTO);
        Assertions.assertThat(productInputDTO)
                .usingRecursiveComparison()
                .isEqualTo(product);
    }
}
