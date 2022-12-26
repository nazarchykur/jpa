package com.example.jpales13sb.services;

import com.example.jpales13sb.entities.ProductLes13sb;
import com.example.jpales13sb.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public void addProduct(String name){
        ProductLes13sb p = new ProductLes13sb();
        p.setName(name);

        productRepository.save(p);
    }

    public List<ProductLes13sb> findProducts() {
        return productRepository.findAll();
    }
}
