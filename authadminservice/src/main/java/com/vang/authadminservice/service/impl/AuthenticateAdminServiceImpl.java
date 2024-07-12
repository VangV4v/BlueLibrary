package com.vang.authadminservice.service.impl;

import com.vang.authadminservice.common.AuthenticateResponseCommon;
import com.vang.authadminservice.common.ServiceCommon;
import com.vang.authadminservice.jwt.JwtService;
import com.vang.authadminservice.model.AuthRequestModel;
import com.vang.authadminservice.service.AuthenticateAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateAdminServiceImpl implements AuthenticateAdminService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthenticateAdminServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<AuthenticateResponseCommon> authenticate(AuthRequestModel model) {

        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));
            if(authentication.isAuthenticated()) {

                String jwt = jwtService.generateToken(model.getUsername());
                AuthenticateResponseCommon response = AuthenticateResponseCommon.builder().
                        isAuthenticated(true).role(ServiceCommon.ROLE_ADMIN).expiration(System.currentTimeMillis()+ (60 * 1000 * 20)+"").jwt(jwt).build();
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