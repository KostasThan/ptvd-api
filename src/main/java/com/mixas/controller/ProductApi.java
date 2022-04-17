package com.mixas.controller;

import com.mixas.domain.model.Product;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "/products", tags = "Products")
public interface ProductApi {

    String BASE_URL = "/products";

    @Operation(summary = "Returns a list of all Products in database")
    @GetMapping(value = BASE_URL)
    List<Product> findAll();

    @Operation(summary = "Saves a new Product to the database")
    @PostMapping(value = BASE_URL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Product post(@RequestBody(required = false) Product product);

    @Operation(summary = "Deletes a Product from the database")
    @DeleteMapping(value = BASE_URL + "{id}")
    ResponseEntity<Void> delete(@PathVariable("id") String id);

}
