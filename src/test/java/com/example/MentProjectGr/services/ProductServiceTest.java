package com.example.MentProjectGr.services;

import com.example.MentProjectGr.entity.Product;
import com.example.MentProjectGr.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Lets writte a simple unit test for ProductService class. I will provide a simple test for the method saveProduct.
// We will use Mockito framework to mock the ProductRepository class.
// You don't need to know how Mockito works, just use it as it is for now. I will send you a link to the documentation regarding
// Mockito framework and testing in general.

// How to run this test? Right click on the class name and select Run 'ProductServiceTest'
// or click on the green arrow near the class name and select Run 'ProductServiceTest'
// or click on the green arrow near the method name and select Run 'whenSaveProductWithNonEmptyValueThenSaveObjectWithDoublePrice'
// or with gradle: ./gradlew test --tests ProductServiceTest or `./gradlew test` to run all tests in the module
// Oups.... we don't use gradle :(
@ExtendWith(MockitoExtension.class) // <--- this annotation is needed to use Mockito framework
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    /*
     Usually, when you write a unit test, you need to test only one method at a time.
        In our case, we need to test the method saveProduct. So, we need to mock the ProductRepository class,
        because we don't want to test the ProductRepository class, we want to test the ProductService class only.
        Mock is like a fake object that you can use to test your code. You can tell the mock object what to return and when.
        So you can test your code in different scenarios:
        - when the ProductRepository returns a product
        - when the ProductRepository returns null
        - when the ProductRepository throws an exception
        - etc.

        Take a look at the test method name! It uses a special naming convention
        https://medium.com/@stefanovskyi/unit-test-naming-conventions-dd9208eadbea
        Actually, the are a lot of them, so you can choose the one you like the most or what is more common in your team.
     */

    @Test
    void whenSaveProductWithNonEmptyValueThenSaveObjectWithDoublePrice() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("TestProduct");
        product.setPrice(50);

        when(productRepository.save(product)).thenReturn(product);

        // Act
        productService.saveProduct(product);

        // Assert that price is doubled
        assertEquals(100.0, product.getPrice(), "Price should be doubled");
        // Assert that the productRepository.save method was called with the product object
        verify(productRepository).save(product);

        // so here we check that the productRepository.save method was called with the product object and the price was doubled
        // All logic for this method for tested.
        // Except cases when DB throws an exception. But you may write another test for this case.
    }
    @Test
    void whenUpdateProductWithNonEmptyValue(){
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("TestProduct");
        product1.setPrice(70);

        when(productRepository.save(product1)).thenReturn(product1);

        productService.updateProduct(product1);

        assertEquals(70, product1.getPrice(),"Price is correct");
        verify(productRepository).save(product1);
    }
    @Test
    void whenDeleteByIdProduct(){
        Long productIdToDelete = 1L;

       doNothing().when(productRepository).deleteById(productIdToDelete);

        productService.deleteProduct(productIdToDelete);

        verify(productRepository, times(1)).deleteById(productIdToDelete);
    }
    @Test
    void whenGetAllProducts(){
        List<Product> products = Arrays.asList(
                new Product(1L, "product1","some desc", 50),
                new Product(2L, "product2","some desc", 70),
                new Product(3L, "product3","some desc", 45)
        );

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(products, result);

    }
    @Test
    void whenGetProductById(){
        Long productId = 1L;
        Product testProduct = new Product(1L,"test", "test", 10 );

        when(productRepository.findById(productId)).thenReturn(Optional.of(testProduct));

        Optional<Product> testResult = productService.getProductById(productId);

        assertTrue(testResult.isPresent());
        assertEquals(testProduct, testResult.get());
    }
    @Test
    void whenGetProductByIdNotFound(){
        Long productId = 999L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Optional<Product> testResult = productService.getProductById(productId);

        assertFalse(testResult.isPresent());

    }

}