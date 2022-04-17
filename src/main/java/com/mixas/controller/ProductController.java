package com.mixas.controller;

import com.mixas.domain.model.Product;
import com.mixas.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<Product> findAll() {
        return productService.getProducts();
    }

    @Override
    public Product post(@RequestBody(required = false) Product product) {
        return productService.save(product);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
