package com.yun.util;

import com.yun.daily.secure.domain.SecureUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class SecureUtil {
    private static final String SECRET = "daily";
    private static final Long TTLMILLIS = 604800L;
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_USERNAME = "username";
    Logger logger = LoggerFactory.getLogger(SecureUtil.class);
    public static String getUsernameFromToken(String token){
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
           username=null;
           //发生异常
        }
        return username;
    }

    public static Boolean  validateToken(String token, UserDetails userDetails){
        SecureUser user = (SecureUser) userDetails;
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername())&&isTokenExpired(token));
    }

    private static boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private static Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        }catch (Exception e){
            expiration = null;
            //发生异常
        }
        return  expiration;
    }

    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            claims = null;
            //发生异常
        }
        return claims;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        String subject = userDetails.getUsername();
        claims.put(CLAIM_KEY_USERNAME,subject);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims,subject);
    }

    public static String generateToken(Map<String ,Object> claims,String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    private static Date generateExpirationDate() {
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis+TTLMILLIS;
        return new Date(expMillis);
    }
}
