package com.example.Ecommerce.Security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "mysecretkeymysecretkeymysecretkey12";

    public String generateToken(UserDetails userDetails){
        HashMap<String, Object> temp = new HashMap<>();
        return Jwts.builder()
                .setClaims(temp)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public <T> T extractClaims(String token, Function<Claims, T> ClaimResolver){
        final Claims claim = extractAllClaims(token);
        return ClaimResolver.apply(claim);
    }
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSignKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
