package com.example.teacherservice.controller;


import com.example.teacherservice.client.KeycloakAuthClient;
import com.example.teacherservice.dto.AuthDTO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final KeycloakAuthClient keycloakAuthClient;

    @Value("${spring.application.name.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;

    @Value("${spring.application.name.security.oauth2.client.registration.keycloak.authorization-grant-type}")
    private String grantType;

    @Value("${spring.application.name.security.oauth2.client.registration.keycloak.client-secret}")
    private String clientSecret;

    public AuthController(KeycloakAuthClient keycloakAuthClient) {
        this.keycloakAuthClient = keycloakAuthClient;
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody AuthDTO authDTO) {
        try {
            String tokenResponse = keycloakAuthClient.getToken(
                    clientId,
                    authDTO.login(),
                    authDTO.password(),
                    grantType,
                    clientSecret
            );
            return ResponseEntity.ok(tokenResponse);
        } catch (feign.FeignException e) {
            return ResponseEntity.status(e.status()).body(e.contentUTF8());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
        }
    }
}
