package com.inventory.api;

import com.inventory.api.model.Product;
import com.inventory.api.repository.ProductRepository;
import com.inventory.api.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductRepository repo;
    private ProductService service;

    @BeforeEach
    void setup() {
        repo = Mockito.mock(ProductRepository.class);
        service = new ProductService(repo);
    }

    @Test
    void increaseStock_success() {
        Product p = new Product("A", "desc", 5, 2);
        p.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(p));
        when(repo.save(any())).thenAnswer(i -> i.getArgument(0));
        Product updated = service.increaseStock(1L, 3);
        assertEquals(8, updated.getStockQuantity());
    }

    @Test
    void decreaseStock_success() {
        Product p = new Product("A", "desc", 5, 2);
        p.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(p));
        when(repo.save(any())).thenAnswer(i -> i.getArgument(0));
        Product updated = service.decreaseStock(1L, 3);
        assertEquals(2, updated.getStockQuantity());
    }

    @Test
    void decreaseStock_insufficient_throws() {
        Product p = new Product("A", "desc", 2, 2);
        p.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(p));
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.decreaseStock(1L, 3));
        assertTrue(ex.getMessage().toLowerCase().contains("insufficient"));
    }

    @Test
    void increaseStock_invalid_amount_throws() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.increaseStock(1L, 0));
        assertTrue(ex.getMessage().toLowerCase().contains("positive"));
    }
}
