package com.mixas.domain.service;

import com.mixas.dao.entities.ProductEntity;
import com.mixas.dao.repositories.ProductRepository;
import com.mixas.domain.model.Product;
import com.mixas.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<Product> getProducts(){
        return productMapper.entityToModel(productRepository.findAll());
    }

    public Product save(Product product){
        ProductEntity productEntity = productMapper.modelToEntity(product);

        return productMapper.entityToModel(productRepository.save(productEntity));
    }

    public void delete(String id){
        productRepository.deleteById(id);
    }
}
