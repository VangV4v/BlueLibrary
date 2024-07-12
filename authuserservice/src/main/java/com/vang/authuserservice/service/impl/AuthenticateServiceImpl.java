package com.vang.authuserservice.service.impl;

import com.vang.authuserservice.common.AuthenticateResponseCommon;
import com.vang.authuserservice.common.ServiceCommon;
import com.vang.authuserservice.jwt.JwtService;
import com.vang.authuserservice.model.AuthRequestModel;
import com.vang.authuserservice.service.AuthenticateService;
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
    public ResponseEntity<AuthenticateResponseCommon> authenticate(AuthRequestModel requestModel) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestModel.getUsername(), requestModel.getPassword()));
            if(authentication.isAuthenticated()) {

                String jwt = jwtService.generateToken(requestModel.getUsername());
                AuthenticateResponseCommon response = AuthenticateResponseCommon.builder().
                        isAuthenticated(true).role(ServiceCommon.ROLE_USER).expiration(System.currentTimeMillis()+ (60 * 1000 * 20)+"").jwt(jwt).build();
                redisTemplate.opsForValue().set(ServiceCommon.REDIS_KEY_USERNAME, requestModel.getUsername());
                redisTemplate.opsForValue().set(ServiceCommon.REDIS_KEY_USERNAME_EXPIRATION, System.currentTimeMillis()+ (60 * 1000 * 20)+"");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (BadCredentialsException e) {

            AuthenticateResponseCommon response = AuthenticateResponseCommon.builder().build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        AuthenticateResponseCommon response = AuthenticateResponseCommon.builder().build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}