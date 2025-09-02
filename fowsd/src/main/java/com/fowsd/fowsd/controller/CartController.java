package com.fowsd.fowsd.controller;

import com.fowsd.fowsd.model.CartItem;
import com.fowsd.fowsd.model.User;
import com.fowsd.fowsd.dto.CartItemDTO;
import com.fowsd.fowsd.repo.UserRepository;
import com.fowsd.fowsd.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final UserRepository userRepo;

    public CartController(CartService cartService, UserRepository userRepo) {
        this.cartService = cartService; this.userRepo = userRepo;
    }

    private User getUser(UserDetails ud) {
        return userRepo.findByEmail(ud.getUsername()).orElseThrow();
    }

    @GetMapping
    public List<CartItem> getCart(@AuthenticationPrincipal UserDetails ud) {
        return cartService.getCartForUser( getUser(ud) );
    }

    @PostMapping("/items")
    public ResponseEntity<?> addItem(@AuthenticationPrincipal UserDetails ud,
                                     @Valid @RequestBody CartItemDTO dto) {
        CartItem item = cartService.addOrUpdateItem(getUser(ud), dto.getProductId(), dto.getQuantity());
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        cartService.removeItem(id);
        return ResponseEntity.noContent().build();
    }
}
