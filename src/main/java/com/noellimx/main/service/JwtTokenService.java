package com.noellimx.main.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
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


  public <T> T _extractClaim(Claims claims, Function<Claims, T> claimsResolver) {
    return claimsResolver.apply(claims);
  }

  public String extractUsername(Claims claims) {
    return _extractClaim(claims, c -> c.getSubject());
  }


  public Claims extractAllClaims(String token, Key key) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
        .getBody();
  }
}
