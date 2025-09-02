package com.fowsd.fowsd.service;

import com.fowsd.fowsd.model.*;
import com.fowsd.fowsd.repo.*;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final CartItemRepository cartRepo;
    private final UserRepository userRepo;

    public OrderService(OrderRepository orderRepo, CartItemRepository cartRepo, UserRepository userRepo) {
        this.orderRepo = orderRepo; this.cartRepo = cartRepo; this.userRepo = userRepo;
    }

    @Transactional
    public Order checkout(User user) {
        List<CartItem> cart = cartRepo.findByUser(user);
        if (cart.isEmpty()) throw new RuntimeException("Cart is empty");

        List<OrderItem> items = cart.stream()
                .map(ci -> new OrderItem(ci.getProduct(), ci.getQuantity(), ci.getPriceAtAdd()))
                .collect(Collectors.toList());

        BigDecimal total = items.stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order(user, Instant.now(), total, items);
        order = orderRepo.save(order);
        cartRepo.deleteByUser(user);
        return order;
    }

    public List<Order> listForUser(User user) { return orderRepo.findByUser(user); }

    public Order getById(Long id) { return orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found")); }
}
