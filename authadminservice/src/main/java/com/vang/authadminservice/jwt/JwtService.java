package com.vang.authadminservice.jwt;

import com.vang.authadminservice.common.ServiceCommon;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public String generateToken(String username) {

        Map<String, String> claims = new HashMap<>();
        claims.put(ServiceCommon.ROLE_FIELD, ServiceCommon.ROLE_ADMIN);
        return createToken(username, claims);
    }

    private String createToken(String username, Map<String, String> claims) {

        return Jwts
                .builder()
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SECRET)))
                .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 20)))
                .subject(username)
                .claims(claims)
                .compact();
    }

}