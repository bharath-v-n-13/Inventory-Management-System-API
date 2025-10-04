package com.inventory.api.repository;

import com.inventory.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByLowStockThresholdIsNotNullAndStockQuantityLessThan(Integer threshold);
}
