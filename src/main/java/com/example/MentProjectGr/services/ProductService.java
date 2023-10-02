package com.example.MentProjectGr.services;

import com.example.MentProjectGr.entity.Product;
import com.example.MentProjectGr.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void saveProduct(Product product) {
        // Let's say we want to double the price all the time. Why? Well just to add some extra logic into the service.
        // Because, usually, service is the place where you put your business logic: validation, calculations, data-generating.

        product.setPrice(product.getPrice() * 2);
        productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
