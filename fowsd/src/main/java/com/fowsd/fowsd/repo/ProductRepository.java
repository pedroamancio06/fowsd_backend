package com.fowsd.fowsd.repo;

import com.fowsd.fowsd.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
