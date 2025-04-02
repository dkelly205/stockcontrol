package com.dkelly.StockControlApp.controller;

import com.dkelly.StockControlApp.entity.Product;
import com.dkelly.StockControlApp.exception.ProductNotFoundException;
import com.dkelly.StockControlApp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product savedProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(
            @RequestParam(value="name", required = false) String name,
            @RequestParam(value="description", required = false) String description){
        List<Product> products = productService.findAll(name, description);
        return ResponseEntity.ok(products);
    }

    @GetMapping(path= "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) throws ProductNotFoundException {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping(path= "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
