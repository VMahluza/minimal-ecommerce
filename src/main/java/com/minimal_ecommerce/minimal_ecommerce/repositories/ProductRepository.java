package com.minimal_ecommerce.minimal_ecommerce.repositories;

import com.minimal_ecommerce.minimal_ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Additional query methods can be defined here
}