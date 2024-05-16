package com.diegobarrioh.api.akdmiaapi.security.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.KeyBuilder;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
@Service
public class JwtTokenService {

    @Value("${components.jwt.secret-key}")
    private String secret;
    @Value("${components.jwt.deprecation-time}")
    private long deprecationTime;


    public String createJwtToken(Authentication authentication) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + deprecationTime);
        JwtBuilder builder = Jwts.builder()
                .issuedAt(now)
                .expiration(validity)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)));
        //set claims

        return builder.compact();

    }

    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException ex) {
            throw new CredentialsExpiredException(ex.getMessage());
        } catch (JwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException(ex.getMessage());
        }

    }
}
