package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private OAuth2AuthorizedClientService clientService; // Inyección del servicio para obtener el token

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Este es un endpoint público, no requiere autenticación.";
    }

    @GetMapping("/user")
    public String userInfo(@AuthenticationPrincipal OAuth2User principal) {
        String name = principal.getAttribute("name");
        String email = principal.getAttribute("email");
        return "Hola, " + name + "! Tu email es: " + email;
    }

    @GetMapping("/token")
    public String getToken(Authentication authentication) {
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
            "google", authentication.getName());
        if (client != null && client.getAccessToken() != null) {
            return client.getAccessToken().getTokenValue();
        } else {
            return "No se pudo obtener el token de acceso.";
        }
    }
}