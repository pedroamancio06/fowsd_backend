package com.fowsd.fowsd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(length = 2000)
    private String description;

    @NotNull
    private BigDecimal price;

    private String category;

    private String imageUrl;

    // getters/setters

    public Product() {}

    public Product(String name, String description, BigDecimal price, String category, String imageUrl) {
        this.name = name; this.description = description; this.price = price; this.category = category; this.imageUrl = imageUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name;}
    public String getDescription() { return description;}
    public void setDescription(String description) { this.description = description;}
    public BigDecimal getPrice() { return price;}
    public void setPrice(BigDecimal price) { this.price = price;}
    public String getCategory() { return category;}
    public void setCategory(String category) { this.category = category;}
    public String getImageUrl() { return imageUrl;}
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl;}
}
