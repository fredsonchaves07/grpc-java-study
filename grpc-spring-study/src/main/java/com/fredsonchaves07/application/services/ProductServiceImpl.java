package com.fredsonchaves07.application.services;

import com.fredsonchaves07.application.dto.ProductInputDTO;
import com.fredsonchaves07.application.dto.ProductOutputDTO;
import com.fredsonchaves07.application.exception.AlreadyExistsException;
import com.fredsonchaves07.application.exception.NotFoundException;
import com.fredsonchaves07.domain.entity.Product;
import com.fredsonchaves07.domain.repository.ProductRepository;
import com.fredsonchaves07.domain.services.IProductService;
import com.fredsonchaves07.util.ProductConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    @Transactional
    public ProductOutputDTO create(ProductInputDTO inputDTO) {
        checkDuplicity(inputDTO.name());
        Product product = ProductConverterUtil.toProduct(inputDTO);
        repository.save(product);
        return ProductConverterUtil.toProductOutputDTO(product);
    }

    @Override
    public ProductOutputDTO findById(Long productId) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new NotFoundException(productId));
        return ProductConverterUtil.toProductOutputDTO(product);
    }

    @Override
    @Transactional
    public void delete(Long productId) {

    }

    @Override
    public List<ProductOutputDTO> findAll() {
        return null;
    }

    private void checkDuplicity(String name) {
        repository.findByNameIgnoreCase(name)
                .ifPresent(exception -> {
                    throw new AlreadyExistsException(name);
                });
    }
}
