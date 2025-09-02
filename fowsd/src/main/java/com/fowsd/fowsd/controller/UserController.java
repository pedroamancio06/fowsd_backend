package com.fowsd.fowsd.controller;

import com.fowsd.fowsd.model.User;
import com.fowsd.fowsd.repo.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepo;
    public UserController(UserRepository userRepo) { this.userRepo = userRepo; }

    @GetMapping("/me")
    public User me(@AuthenticationPrincipal UserDetails ud) {
        return userRepo.findByEmail(ud.getUsername()).orElseThrow();
    }
}
