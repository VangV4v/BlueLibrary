package com.vang.authemployeeservice.jwt;

import com.vang.authemployeeservice.common.ServiceCommon;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwe;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.KeyRequest;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    private static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public String generateToken(String username) {

        Map<String, String> claims = Map.of(ServiceCommon.FIELD_ROLE, ServiceCommon.ROLE_EMPLOYEE);
        return createToken(username, claims);
    }

    public boolean validateToken(String token, UserDetails userDetails) {

        return new Date().before(extractClaim(token, Claims::getExpiration)) && userDetails.getUsername().equals(extractUsername(token));
    }

    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getClaims(String token) {

        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String createToken(String username, Map<String, String> claims) {

        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
        return Jwts
                .builder()
                .claims(claims)
                .subject(username)
                .signWith(getKey())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 20))
                .compact();
    }

    private Key getKey() {

        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }
}