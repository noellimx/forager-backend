package com.noellimx.main.service;


import io.jsonwebtoken.Claims;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthService {

  JwtTokenService tokenService;

  Key key;

  @Autowired
  public JwtAuthService(JwtTokenService tokenService, Key appSignInKey) {
    this.tokenService = tokenService;
    this.key = appSignInKey;
  }

  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return this.tokenService.generateToken(extraClaims, userDetails, key);
  }

  public <T> T _extractClaim(Claims claims, Function<Claims, T> claimsResolver) {
    return claimsResolver.apply(claims);
  }

  public boolean isTokenValid(String token) {
    Claims claims = extractAllClaims(token);
    return _isTokenValid_isTokenExpired(
        claims);
  }

  public boolean _isTokenValid_isTokenExpired(Claims token) {
    return _extractClaim(token, Claims::getExpiration).before(new Date());
  }

  public String extractUsername(Claims claims) {
    return _extractClaim(claims, c -> c.getSubject());
  }

  public String extractUsername(String token) {
    return extractUsername(extractAllClaims(token));
  }

  private Claims extractAllClaims(String token) {
    return this.tokenService.extractAllClaims(token, key);
  }
}
