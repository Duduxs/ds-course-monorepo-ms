package com.edudev.msoauth.config.security;

import com.edudev.msoauth.entities.User;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Component
public class JwtProvider {

    @Value("${dscourse.auth.jwtSecret}")
    private String secret;

    @Value("${dscourse.auth.jwtExpirationMs}")
    private int expirationMs;

    private static Logger log = LoggerFactory.getLogger(JwtProvider.class);

    public String generateJwt(Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();

        final var today = new Date();

        final String roles = userPrincipal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(userPrincipal.getId().toString())
                .claim("username", userPrincipal.getUsername())
                .claim("roles", roles)
                .setIssuedAt(today)
                .setExpiration(new Date((today).getTime() + expirationMs))
                .signWith(HS512, secret)
                .compact();
    }
}
