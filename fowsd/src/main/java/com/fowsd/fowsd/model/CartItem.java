package com.fowsd.fowsd.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Product product;

    private Integer quantity;

    private BigDecimal priceAtAdd;

    public CartItem() {}

    public CartItem(User user, Product product, Integer quantity, BigDecimal priceAtAdd) {
        this.user = user; this.product = product; this.quantity = quantity; this.priceAtAdd = priceAtAdd;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user;}
    public void setUser(User user) { this.user = user;}
    public Product getProduct() { return product;}
    public void setProduct(Product product) { this.product = product;}
    public Integer getQuantity() { return quantity;}
    public void setQuantity(Integer quantity) { this.quantity = quantity;}
    public BigDecimal getPriceAtAdd() { return priceAtAdd;}
    public void setPriceAtAdd(BigDecimal priceAtAdd) { this.priceAtAdd = priceAtAdd;}
}
