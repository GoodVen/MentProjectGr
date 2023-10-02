package com.example.MentProjectGr.controllers;

import com.example.MentProjectGr.entity.Product;
import com.example.MentProjectGr.entity.ProductConverter;
import com.example.MentProjectGr.models.ProductDTO;
import com.example.MentProjectGr.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController("/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public List<ProductDTO> getAllProducts() {

        return productService.getAllProducts().stream()
                .map(ProductConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getProductById(@PathVariable("id") Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/product/create")
    public void createProduct(@RequestBody  ProductDTO product) throws IOException {
        // You need to convet ProductDTO to Product
        productService.saveProduct(ProductConverter.convertToEntity(product));
    }

    @PutMapping("/product/{id}")
    public void productUpdate(@RequestBody ProductDTO product) {
        productService.updateProduct(ProductConverter.convertToEntity(product));
    }

    @DeleteMapping("/product/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
// no need to have empty line here
}
