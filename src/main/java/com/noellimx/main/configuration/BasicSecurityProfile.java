package com.noellimx.main.configuration;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class BasicSecurityProfile {

  private static final String UNSAFE_SIGNING_KEY = "ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb";

  @Bean(name = "appSignInKey")
  public static Key signInKey() {
    byte[] key = Decoders.BASE64.decode(UNSAFE_SIGNING_KEY);
    return Keys.hmacShaKeyFor(key);
  }

  public static Key signInKey(String keyString) {
    byte[] key = Decoders.BASE64.decode(keyString);
    return Keys.hmacShaKeyFor(key);
  }
}