package com.fowsd.fowsd.controller;

import com.fowsd.fowsd.model.Order;
import com.fowsd.fowsd.model.User;
import com.fowsd.fowsd.repo.UserRepository;
import com.fowsd.fowsd.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserRepository userRepo;

    public OrderController(OrderService orderService, UserRepository userRepo) {
        this.orderService = orderService; this.userRepo = userRepo;
    }

    private User getUser(UserDetails ud) {
        return userRepo.findByEmail(ud.getUsername()).orElseThrow();
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(@AuthenticationPrincipal UserDetails ud) {
        Order order = orderService.checkout(getUser(ud));
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public List<Order> list(@AuthenticationPrincipal UserDetails ud) {
        return orderService.listForUser(getUser(ud));
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable Long id) { return orderService.getById(id); }
}
