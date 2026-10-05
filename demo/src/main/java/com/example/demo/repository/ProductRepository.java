package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public ProductRepository() {
        // Dữ liệu mẫu
        products.add(new Product(idCounter.getAndIncrement(), "Laptop Dell", 15000000.0, "Laptop cao cấp", 10));
        products.add(new Product(idCounter.getAndIncrement(), "iPhone 15", 22000000.0, "Điện thoại thông minh", 5));
        products.add(new Product(idCounter.getAndIncrement(), "Tai nghe Sony", 3000000.0, "Tai nghe chống ồn", 15));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Product findById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Product save(Product product) {
        product.setId(idCounter.getAndIncrement());
        products.add(product);
        return product;
    }

    public Product update(Long id, Product updatedProduct) {
        Product existing = findById(id);
        if (existing != null) {
            existing.setName(updatedProduct.getName());
            existing.setPrice(updatedProduct.getPrice());
            existing.setDescription(updatedProduct.getDescription());
            existing.setQuantity(updatedProduct.getQuantity());
            return existing;
        }
        return null;
    }

    public boolean deleteById(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }
}