package com.example.MentProjectGr.controllers;

import com.example.MentProjectGr.entity.Product;
import com.example.MentProjectGr.entity.ProductConverter;
import com.example.MentProjectGr.models.ProductDTO;
import com.example.MentProjectGr.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController("/product")
public class ProductController {

    private ProductService productService;

    @GetMapping("/")
    public List<ProductDTO> getAllProducts() {
        // Here we are converting the list of products to list of ProductDTO with the help of stream API
        // map function takes each product object and calls convertToDTO of ProductConverter class
        // that converts the product object to ProductDTO object.
        // In other words function MAP take object of type A and convert it to object of type B
        // In the end we collect all converted objects to list
        return productService.getAllProducts().stream()
                .map(ProductConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable("id") Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/")
    public void createProduct(ProductDTO product) throws IOException {
        // You need to convet ProductDTO to Product
        productService.saveProduct(ProductConverter.convertToEntity(product));
    }

    @PutMapping("/product/{id}")
    public void productUpdate(@RequestBody ProductDTO product) {
        productService.updateProduct(ProductConverter.convertToEntity(product));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
// no need to have empty line here
}
