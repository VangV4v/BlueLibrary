package com.vang.authemployeeservice.service.impl;

import com.vang.authemployeeservice.common.AuthenticateResponseCommon;
import com.vang.authemployeeservice.common.ServiceCommon;
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
    public ResponseEntity<AuthenticateResponseCommon> authenticate(AuthRequestModel requestModel) {

        AuthenticateResponseCommon response;
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestModel.getUsername(), requestModel.getPassword()));
            if(authentication.isAuthenticated()) {

                redisTemplate.opsForValue().set(ServiceCommon.REDIS_KEY_USERNAME, requestModel.getUsername());
                redisTemplate.opsForValue().set(ServiceCommon.REDIS_KEY_USERNAME_EXPIRATION, System.currentTimeMillis()+(1000 * 60 * 20)+"");
                String token = jwtService.generateToken(requestModel.getUsername());
                response = AuthenticateResponseCommon.builder().isAuthenticated(true).role(ServiceCommon.ROLE_EMPLOYEE).expiration(System.currentTimeMillis()+(1000 * 60 * 20)+"").jwt(token).build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (BadCredentialsException badCredentialsException) {

            response = AuthenticateResponseCommon.builder().build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response = AuthenticateResponseCommon.builder().build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}