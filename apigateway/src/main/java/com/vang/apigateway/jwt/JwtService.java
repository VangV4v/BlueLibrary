package com.vang.apigateway.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public String getRole(String jwt) {

        Claims claims = getClaims(jwt);
        return claims.get("role", String.class);
    }

    public String extractUsername(String jwt) {

        return extractUsername(jwt, Claims::getSubject);
    }

    private <T> T extractUsername(String jwt, Function<Claims, T> claimsExtractor) {

        Claims claims = getClaims(jwt);
        return claimsExtractor.apply(claims);
    }

    private Claims getClaims(String jwt) {

        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SECRET)))
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

}