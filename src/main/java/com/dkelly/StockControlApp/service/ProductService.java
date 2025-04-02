package com.dkelly.StockControlApp.service;

import com.dkelly.StockControlApp.entity.Product;
import com.dkelly.StockControlApp.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll(String name, String description);

    Product findById(Long id) throws ProductNotFoundException;

    void deleteById(Long id);



}
