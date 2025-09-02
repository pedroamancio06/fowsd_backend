package com.fowsd.fowsd.service;

import com.fowsd.fowsd.model.Product;
import com.fowsd.fowsd.repo.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository repo) { this.repo = repo; }

    public List<Product> listAll() { return repo.findAll(); }
    public Product get(Long id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found")); }
    public Product create(Product p) { return repo.save(p); }
    public Product update(Long id, Product p) {
        Product exist = get(id);
        exist.setName(p.getName());
        exist.setDescription(p.getDescription());
        exist.setPrice(p.getPrice());
        exist.setCategory(p.getCategory());
        exist.setImageUrl(p.getImageUrl());
        return repo.save(exist);
    }
    public void delete(Long id) { repo.deleteById(id); }
}
