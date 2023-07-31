package com.noellimx.main.test.service;


import com.noellimx.main.configuration.BasicSecurityProfile;
import com.noellimx.main.service.JwtAuthService;
import com.noellimx.main.service.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class JwtAuthServiceTest {

  JwtAuthService service;
  JwtTokenService tokenService;

  private static final String testKey = "fb8e20fc2e4c3f248c60c39bd652f3c1347298bb977b8b4d5903b85055620603";


  @Autowired
  public JwtAuthServiceTest(JwtTokenService tokenService) {
    this.service = new JwtAuthService(tokenService, BasicSecurityProfile.signInKey(testKey));
    this.tokenService = tokenService;
  }

  @Test
  void GeneratedJWT_ShouldBeValidated() {

    String username = "testuser1";
    String password = "password";
    UserDetails userDetails = User.builder().username(username).password(password).build();
    String token = this.service.generateToken(userDetails);

    Boolean got = this.service.isTokenValid(token);

    Claims claims = this.service.extractAllClaims(token);

    Assertions.assertNotNull(claims.getSubject());
    Assertions.assertNotNull(claims.getIssuedAt());
    Assertions.assertNotNull(claims.getExpiration());
    Assertions.assertTrue(got);
  }

  @Test
  void GeneratedJWT_ShouldBeNotValidatedByAnotherKey() {
    String username = "testuser1";
    String password = "password";
    UserDetails userDetails = User.builder().username(username).password(password).build();
    String token = this.service.generateToken(userDetails);
    JwtAuthService anotherService = new JwtAuthService(tokenService, BasicSecurityProfile.signInKey(
        "b05efba3cb00bbb571bc6616e14963250017729bd07863c8d981ac342ba0da92"));
    
    Assertions.assertThrows(SignatureException.class, () -> anotherService.isTokenValid(token));
  }


}
