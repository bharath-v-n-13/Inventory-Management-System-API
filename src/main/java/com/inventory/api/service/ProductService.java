package com.inventory.api.service;

import com.inventory.api.model.Product;
import com.inventory.api.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product createProduct(Product product) {
        // Ensure no negative stock on creation
        if (product.getStockQuantity() < 0) {
            throw new IllegalArgumentException("stockQuantity cannot be negative");
        }
        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    public Product updateProduct(Long id, Product incoming) {
        Product existing = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        if (incoming.getName() != null) existing.setName(incoming.getName());
        if (incoming.getDescription() != null) existing.setDescription(incoming.getDescription());
        // If stock is being updated directly, validate it is not negative
        if (incoming.getStockQuantity() < 0) throw new IllegalArgumentException("stockQuantity cannot be negative");
        existing.setStockQuantity(incoming.getStockQuantity());
        existing.setLowStockThreshold(incoming.getLowStockThreshold());
        return repository.save(existing);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Product increaseStock(Long id, int amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount must be positive");
        Product p = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        long newQty = (long) p.getStockQuantity() + amount;
        if (newQty > Integer.MAX_VALUE) throw new IllegalArgumentException("quantity overflow");
        p.setStockQuantity((int) newQty);
        return repository.save(p);
    }

    @Transactional
    public Product decreaseStock(Long id, int amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount must be positive");
        Product p = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        if (p.getStockQuantity() < amount) {
            throw new IllegalArgumentException("Insufficient stock available");
        }
        p.setStockQuantity(p.getStockQuantity() - amount);
        return repository.save(p);
    }

    public List<Product> listLowStockProducts() {
        // Return products whose lowStockThreshold is set and stockQuantity < threshold
        return repository.findAll().stream()
                .filter(p -> p.getLowStockThreshold() != null && p.getStockQuantity() < p.getLowStockThreshold())
                .toList();
    }
}
