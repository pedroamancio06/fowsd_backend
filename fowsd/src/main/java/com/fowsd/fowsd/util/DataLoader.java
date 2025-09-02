package com.fowsd.fowsd.util;

import com.fowsd.fowsd.model.*;
import com.fowsd.fowsd.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final BCryptPasswordEncoder encoder;

    public DataLoader(UserRepository userRepo, ProductRepository productRepo, BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo; this.productRepo = productRepo; this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepo.existsByEmail("admin@fowsd.com")) {
            User admin = new User("Admin", "admin@fowsd.com", encoder.encode("admin123"), Set.of(Role.ROLE_ADMIN, Role.ROLE_USER));
            userRepo.save(admin);
        }
        if (!userRepo.existsByEmail("user@fowsd.com")) {
            User u = new User("User", "user@fowsd.com", encoder.encode("password"), Set.of(Role.ROLE_USER));
            userRepo.save(u);
        }

        if (productRepo.count() == 0) {
            productRepo.save(new Product("Kit IoT Soil Monitor Pro", "Sistema completo de monitoramento do solo com sensores de umidade e nutrientes", new BigDecimal("2499.00"), "Monitores", null));
            productRepo.save(new Product("Drone Agrícola Scanner", "Drone especializado em mapeamento e análise do solo com câmera multispectral", new BigDecimal("5999.00"), "Drones", null));
            productRepo.save(new Product("Plano Análise Premium", "Análise completa do solo com relatório detalhado e recomendações personalizadas", new BigDecimal("129.90"), "Serviços", null));
        }
    }
}
