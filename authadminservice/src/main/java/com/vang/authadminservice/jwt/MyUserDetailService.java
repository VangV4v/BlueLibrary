package com.vang.authadminservice.jwt;

import com.vang.authadminservice.common.ServiceCommon;
import com.vang.authadminservice.grpc.AuthenticateAdminGrpcClientImpl;
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
public class MyUserDetailService implements UserDetailsService {

    private final AuthenticateAdminGrpcClientImpl authenticateAdminGrpcClient;

    @Autowired
    public MyUserDetailService(final AuthenticateAdminGrpcClientImpl authenticateAdminGrpcClient) {
        this.authenticateAdminGrpcClient = authenticateAdminGrpcClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String password = authenticateAdminGrpcClient.authenticateAdmin(username);
        List<GrantedAuthority> list = List.of(new SimpleGrantedAuthority(ServiceCommon.ROLE_ADMIN));
        if(StringUtils.isEmpty(password)) {

            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new User(username, password, list);
    }

}