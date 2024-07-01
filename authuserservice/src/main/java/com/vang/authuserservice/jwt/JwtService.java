package com.vang.authuserservice.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public String generateToken(String username) {

        Map<String, String> claims = Map.of("role", "ROLE_USER");
        return createToken(claims, username);
    }

    private String createToken(Map<String, String> claims, String username) {

        return Jwts
                .builder()
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SECRET)))
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + (1000 * 20)))
                .claims(claims)
                .compact();
    }

}