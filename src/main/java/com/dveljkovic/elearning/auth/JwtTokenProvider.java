package com.dveljkovic.elearning.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtTokenProvider {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String email, int userId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .signWith(SECRET_KEY)
                .compact();
    }
}
