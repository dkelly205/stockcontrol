package com.dkelly.StockControlApp.service.impl;

import com.dkelly.StockControlApp.entity.Product;
import com.dkelly.StockControlApp.exception.ProductNotFoundException;
import com.dkelly.StockControlApp.repository.ProductRepository;
import com.dkelly.StockControlApp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product){
        return productRepository.save(product);
    }


    @Override
    public List<Product> findAll(String name, String description) {
        if(StringUtils.hasText(name)){
            return productRepository.findByNameContainingIgnoreCase(name);
        } else if(StringUtils.hasText(description)){
            return productRepository.findByDescriptionContainingIgnoreCase(description);
        } else {
            return productRepository.findAll();
        }

    }

    public Product findById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }
}
