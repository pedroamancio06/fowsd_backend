package com.fowsd.fowsd.service;

import com.fowsd.fowsd.dto.*;
import com.fowsd.fowsd.model.Role;
import com.fowsd.fowsd.model.User;
import com.fowsd.fowsd.repo.UserRepository;
import com.fowsd.fowsd.security.JwtUtil;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.Set;

@Service
public class AuthService {
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authManager, UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authManager = authManager; this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; this.jwtUtil = jwtUtil;
    }

    @Transactional
    public String register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        User u = new User(req.getName(), req.getEmail(),
                passwordEncoder.encode(req.getPassword()), Set.of(Role.ROLE_USER));
        userRepository.save(u);
        return jwtUtil.generateToken(u.getEmail());
    }

    public String login(String email, String password) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        // if ok, generate token
        return jwtUtil.generateToken(email);
    }
}
