package com.fredsonchaves07.domain.services;

import com.fredsonchaves07.application.dto.ProductInputDTO;
import com.fredsonchaves07.application.dto.ProductOutputDTO;

import java.util.List;

public interface IProductService {

    ProductOutputDTO create(ProductInputDTO inputDTO);

    ProductOutputDTO findById(Long productId);

    void delete(Long productId);

    List<ProductOutputDTO> findAll();
}
