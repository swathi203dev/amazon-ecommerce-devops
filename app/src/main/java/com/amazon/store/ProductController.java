package com.amazon.store;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    record Product(int id, String name, double price, String category) {}

    private final List<Product> products = List.of(
        new Product(1, "Laptop", 55000, "Electronics"),
        new Product(2, "Smartphone", 25000, "Electronics"),
        new Product(3, "Headphones", 2000, "Accessories"),
        new Product(4, "Smart Watch", 5000, "Accessories"),
        new Product(5, "Keyboard", 1500, "Computer Accessories")
    );

    @GetMapping
    public List<Product> getProducts() {
        return products;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return products.stream()
            .filter(product -> product.id() == id)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
