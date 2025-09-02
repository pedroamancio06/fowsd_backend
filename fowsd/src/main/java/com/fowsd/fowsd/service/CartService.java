package com.fowsd.fowsd.service;

import com.fowsd.fowsd.model.*;
import com.fowsd.fowsd.repo.*;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {
    private final CartItemRepository cartRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public CartService(CartItemRepository cartRepo, ProductRepository productRepo, UserRepository userRepo) {
        this.cartRepo = cartRepo; this.productRepo = productRepo; this.userRepo = userRepo;
    }

    public List<CartItem> getCartForUser(User user) { return cartRepo.findByUser(user); }

    @Transactional
    public CartItem addOrUpdateItem(User user, Long productId, int qty) {
        Product prod = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        CartItem existing = cartRepo.findByUserAndProductId(user, productId).orElse(null);
        if (existing == null) {
            CartItem item = new CartItem(user, prod, qty, prod.getPrice());
            return cartRepo.save(item);
        } else {
            existing.setQuantity(Math.max(0, qty));
            if (existing.getQuantity() == 0) {
                cartRepo.delete(existing);
                return null;
            }
            return cartRepo.save(existing);
        }
    }

    public void removeItem(Long cartItemId) { cartRepo.deleteById(cartItemId); }

    public void clearCart(User user) { cartRepo.deleteByUser(user); }

    public BigDecimal total(User user) {
        return cartRepo.findByUser(user).stream()
                .map(i -> i.getPriceAtAdd().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
