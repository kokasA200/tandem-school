package com.example.teacherservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "keycloakAuthClient", url = "${spring.application.security.oauth2.registration.keycloak.token-uri}")
public interface KeycloakAuthClient {

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String getToken(
            @RequestParam("client_id") String clientId,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_secret") String clientSecret
    );
}
