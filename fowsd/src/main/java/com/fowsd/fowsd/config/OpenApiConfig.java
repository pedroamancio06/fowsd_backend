package com.fowsd.fowsd.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "FOWSD API", version = "1.0", description = "API for FOWSD mobile app"))
@Configuration
public class OpenApiConfig {}
