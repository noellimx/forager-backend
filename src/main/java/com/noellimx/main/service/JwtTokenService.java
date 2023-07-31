package com.noellimx.main.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenService {

  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, Key key) {
    return Jwts.builder().setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
        .signWith(key, SignatureAlgorithm.HS256).compact();
  }

  public Claims extractAllClaimsFromToken(String token, Key key) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
        .getBody();
  }
}
