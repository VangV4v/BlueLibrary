package com.vang.authemployeeservice.service.impl;

import com.vang.authemployeeservice.jwt.JwtService;
import com.vang.authemployeeservice.model.AuthRequestModel;
import com.vang.authemployeeservice.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public AuthenticateServiceImpl(JwtService jwtService, AuthenticationManager authenticationManager, RedisTemplate<String, String> redisTemplate) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ResponseEntity<String> authenticate(AuthRequestModel requestModel) {

        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestModel.getUsername(), requestModel.getPassword()));
            if(authentication.isAuthenticated()) {
                redisTemplate.opsForValue().set("usernameOfEmployee", requestModel.getUsername());
                redisTemplate.opsForValue().set("usernameOfEmployeeExpiration", System.currentTimeMillis()+(1000 * 60 * 20)+"");
                String token = jwtService.generateToken(requestModel.getUsername());
                System.out.println(redisTemplate.opsForValue().get("usernameOfEmployee"));
                System.out.println(redisTemplate.opsForValue().get("usernameOfEmployeeExpiration"));
                return new ResponseEntity<>(token, HttpStatus.OK);
            }
        } catch (BadCredentialsException badCredentialsException) {
            return new ResponseEntity<>("Login Fail", HttpStatus.OK);
        }
        return new ResponseEntity<>("Login Fail", HttpStatus.OK);
    }

}