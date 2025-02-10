package com.minimal_ecommerce.minimal_ecommerce.services;

import com.minimal_ecommerce.minimal_ecommerce.models.Category;
import com.minimal_ecommerce.minimal_ecommerce.models.Product;
import com.minimal_ecommerce.minimal_ecommerce.repositories.CategoryRepository;
import com.minimal_ecommerce.minimal_ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    List<Product> findAll();

    Product findById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product productDetails);
    void deleteProduct(Long id);

}
