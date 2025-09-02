package com.fowsd.fowsd.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    private Instant createdAt;

    private BigDecimal total;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    public Order() {}

    public Order(User user, Instant createdAt, BigDecimal total, List<OrderItem> items) {
        this.user = user; this.createdAt = createdAt; this.total = total; this.items = items;
    }

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id;}
    public User getUser() { return user;}
    public void setUser(User user) { this.user = user;}
    public Instant getCreatedAt() { return createdAt;}
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt;}
    public BigDecimal getTotal() { return total;}
    public void setTotal(BigDecimal total) { this.total = total;}
    public List<OrderItem> getItems() { return items;}
    public void setItems(List<OrderItem> items) { this.items = items;}
}
