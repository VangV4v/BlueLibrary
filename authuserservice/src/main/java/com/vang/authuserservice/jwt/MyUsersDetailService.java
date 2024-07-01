package com.vang.authuserservice.jwt;

import com.vang.authuserservice.common.ServiceCommon;
import com.vang.authuserservice.grpc.AuthenticateUserGrpcClientImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUsersDetailService implements UserDetailsService {

    private final AuthenticateUserGrpcClientImpl authenticateUserGrpcClient;

    @Autowired
    public MyUsersDetailService(AuthenticateUserGrpcClientImpl authenticateUserGrpcClient) {
        this.authenticateUserGrpcClient = authenticateUserGrpcClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String password = authenticateUserGrpcClient.authenticateUser(username);
        List<GrantedAuthority> grants = List.of(new SimpleGrantedAuthority(ServiceCommon.ROLE_USER));
        if(StringUtils.isEmpty(password)) {

            throw new UsernameNotFoundException(ServiceCommon.ERR001);
        }
        return new User(username, password, grants);
    }

}