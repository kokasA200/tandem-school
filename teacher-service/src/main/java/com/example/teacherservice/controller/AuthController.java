package com.example.teacherservice.controller;

import com.example.teacherservice.dto.AuthDTO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Setter
@RestController
public class AuthController {

    @Value("${spring.application.security.oauth2.registration.keycloak.client-id}")
    private String clientId;

    @Value("${spring.application.resource-url}")
    private String resourceServerUrl;

    @Value("${spring.application.security.oauth2.registration.keycloak.authorization-grant-type}")
    private String grantType;

    @Value("${spring.application.security.oauth2.registration.keycloak.client-secret}")
    private String secret;


    @GetMapping("/auth")
    public String auth(@RequestBody AuthDTO authDTO) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var body = "client_id=" + clientId +
                "&username=" + authDTO.login() +
                "&password=" + authDTO.password() +
                "&client_secret+" + secret +
                "&grant_type=" + grantType;

        var requestEntity = new HttpEntity<>(body, headers);
        var restTemplate = new RestTemplate();

        var responce = restTemplate.exchange(
                resourceServerUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        if (responce.getStatusCode().value() == 200) {
            return responce.getBody();
        }
        return null;
    }

}
