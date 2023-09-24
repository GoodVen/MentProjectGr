package com.example.MentProjectGr.defaultProducts;

import com.example.MentProjectGr.entity.ProductConverter;
import com.example.MentProjectGr.models.ProductDTO;
import com.example.MentProjectGr.repositories.ProductRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    private final ProductRepository productRepository;

    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ProductDTO product1 = new ProductDTO(1L, "Pasta", "some pasta", 100);
        ProductDTO product2 = new ProductDTO(2L, "Pasta1", "some pasta", 200);
        ProductDTO product3 = new ProductDTO(3L, "Pasta2", "some pasta", 300);
        productRepository.save(ProductConverter.convertToEntity(product1));
        productRepository.save(ProductConverter.convertToEntity(product2));
        productRepository.save(ProductConverter.convertToEntity(product3));
    }
}
