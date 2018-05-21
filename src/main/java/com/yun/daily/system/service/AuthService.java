package com.yun.daily.system.service;

import com.yun.util.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private SecureUtil secureUtil;
    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,SecureUtil secureUtil){
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.secureUtil = secureUtil;
    }

    public String login(String account, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(account,password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(account);
        final String token = secureUtil.generateToken(userDetails);
        return token;
    }
}
