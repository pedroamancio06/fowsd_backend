package com.fowsd.fowsd.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Product product;

    private Integer quantity;

    private BigDecimal price;

    public OrderItem() {}

    public OrderItem(Product product, Integer quantity, BigDecimal price) {
        this.product = product; this.quantity = quantity; this.price = price;
    }

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id;}
    public Product getProduct() { return product;}
    public void setProduct(Product product) { this.product = product;}
    public Integer getQuantity() { return quantity;}
    public void setQuantity(Integer quantity) { this.quantity = quantity;}
    public BigDecimal getPrice() { return price;}
    public void setPrice(BigDecimal price) { this.price = price;}
}
