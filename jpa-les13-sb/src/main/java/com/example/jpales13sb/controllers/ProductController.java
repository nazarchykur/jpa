package com.example.jpales13sb.controllers;

import com.example.jpales13sb.entities.ProductLes13sb;
import com.example.jpales13sb.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/{name}")
    public void addProduct(@PathVariable("name") String name) {
        productService.addProduct(name);
    }

    @GetMapping
    public List<ProductLes13sb> findProducts() {
        return productService.findProducts();
    }
}
