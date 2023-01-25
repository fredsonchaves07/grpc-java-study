package com.fredsonchaves07.util;

import com.fredsonchaves07.application.dto.ProductInputDTO;
import com.fredsonchaves07.application.dto.ProductOutputDTO;
import com.fredsonchaves07.domain.entity.Product;

public class ProductConverterUtil {

    public static ProductOutputDTO toProductOutputDTO(Product product) {
        return new ProductOutputDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantityInStock()
        );
    }

    public static Product toProduct(ProductInputDTO product) {
        return new Product(
                null,
                product.name(),
                product.price(),
                product.quantityInStock()
        );
    }
}
