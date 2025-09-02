package com.fowsd.fowsd.repo;

import com.fowsd.fowsd.model.Order;
import com.fowsd.fowsd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
