package com.example.teacherservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
public class JwtDecoderConfig {

    private static final String JWT_SECRET = "U18g2J555hbLPRxyWBYLX13ujGHbLcMq";

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKey =
                new SecretKeySpec(JWT_SECRET.getBytes(StandardCharsets.UTF_8), "HMACSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }
}




