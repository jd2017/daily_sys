package com.yun.daily.secure.filter;

import com.yun.util.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecureAuthenticationTokenFilter extends OncePerRequestFilter{
    private final String HEADER="Authorization";
    private final String TOKENHEAN = "Bearer ";
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SecureUtil secureUtil;
    @Autowired
    private RequestCache requestCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeather =request.getHeader(HEADER);
        // request匹配，则取出，该操作同时会将缓存的request从session中删除
//        HttpServletRequest wrappedSavedRequest = requestCache.getMatchingRequest(
//                (HttpServletRequest) request, (HttpServletResponse) response);

        if(authHeather !=null &&authHeather.startsWith(TOKENHEAN)){//
            final  String authToken = authHeather.substring(TOKENHEAN.length());
            String username = secureUtil.getUsernameFromToken(authToken);
            if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if(secureUtil.validateToken(authToken,userDetails)){
                    UsernamePasswordAuthenticationToken  authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            chain.doFilter(request,response);
        }else{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication!=null&&authentication.isAuthenticated()) {//已登录
                chain.doFilter(request,response);
            } else {
                if(request.getRequestURL().indexOf("/api/v1")>=0){
                    requestCache.saveRequest(request,response);
                    response.sendRedirect(request.getContextPath() + "/login");
                }else{
                    chain.doFilter(request,response);
                }
            }
        }
        // 优先使用缓存的request
//        chain.doFilter(wrappedSavedRequest == null ? request : wrappedSavedRequest,
//                response);
    }
}
