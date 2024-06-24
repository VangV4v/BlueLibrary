package com.vang.authemployeeservice.jwt;

import com.vang.authemployeeservice.common.ServiceCommon;
import com.vang.authemployeeservice.grpc.EmployeeServiceGrpcClientImpl;
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
public class MyUserDetailsService implements UserDetailsService {

    private final EmployeeServiceGrpcClientImpl employeeServiceGrpcClient;

    @Autowired
    public MyUserDetailsService(EmployeeServiceGrpcClientImpl employeeServiceGrpcClient) {
        this.employeeServiceGrpcClient = employeeServiceGrpcClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String password = employeeServiceGrpcClient.getPassword(username);
        List<GrantedAuthority> grants = List.of(new SimpleGrantedAuthority(ServiceCommon.ROLE_EMPLOYEE));
        return new User(username, password, grants);
    }

}