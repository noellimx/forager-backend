package com.noellimx.main.test.config;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@TestConfiguration
@Component
public class TestSecurityConfiguration {


  @Autowired
  BasicSecurityProfile basicSecurityProfile;

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

    UserDetails testUser = User.builder().username(basicSecurityProfile.getUsername())
        .password(passwordEncoder().encode(basicSecurityProfile.getPassword()))
        .roles("USER", "ADMIN")
        .build();

    return new InMemoryUserDetailsManager(testUser);
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable());
    http.httpBasic(Customizer.withDefaults());
    http.authorizeHttpRequests((authorizeHttpRequests) ->
        authorizeHttpRequests
            .requestMatchers(antMatcher(HttpMethod.PUT, "/**")).hasRole("USER")
            .anyRequest().authenticated()
    );
    return http.build();
  }
}