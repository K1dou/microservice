package com.kidou.hr_gateway.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @GetMapping("/userInfo")
    public Map<String, Object> getAuthenticationDetails(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();

        if (authentication == null) {
            response.put("message", "Usuário não autenticado ou contexto de segurança não inicializado.");
            return response;
        }

        if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
            Object principal = oauthToken.getPrincipal();

            if (principal instanceof OidcUser oidcUser) {
                response.put("name", oidcUser.getFullName());
                response.put("email", oidcUser.getEmail());
                response.put("id_token", oidcUser.getIdToken().getTokenValue());
                response.put("attributes", oidcUser.getAttributes());
            } else if (principal instanceof DefaultOAuth2User defaultUser) {
                response.put("name", defaultUser.getAttribute("name"));
                response.put("email", defaultUser.getAttribute("email"));
                response.put("attributes", defaultUser.getAttributes());
            } else {
                response.put("message", "Usuário autenticado, mas não foi possível extrair informações.");
            }

            response.put("authorities", oauthToken.getAuthorities());
            return response;
        }

        response.put("message", "Tipo de autenticação inesperado.");
        return response;
    }



}

