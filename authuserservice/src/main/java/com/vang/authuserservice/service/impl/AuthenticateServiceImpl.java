package com.vang.authuserservice.service.impl;

import com.vang.authuserservice.common.ServiceCommon;
import com.vang.authuserservice.jwt.JwtService;
import com.vang.authuserservice.model.AuthRequestModel;
import com.vang.authuserservice.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public AuthenticateServiceImpl(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<String> authenticate(AuthRequestModel requestModel) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestModel.getUsername(), requestModel.getPassword()));
            if(authentication.isAuthenticated()) {

                String jwt = jwtService.generateToken(requestModel.getUsername());
                return new ResponseEntity<>(jwt, HttpStatus.OK);
            }
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(ServiceCommon.ERR001, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(ServiceCommon.ERR001, HttpStatus.UNAUTHORIZED);
    }

}