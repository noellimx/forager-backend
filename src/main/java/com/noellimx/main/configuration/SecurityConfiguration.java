package com.noellimx.main.configuration;

import com.noellimx.main.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Autowired
  public AuthenticationManager authenticationManager(HttpSecurity http,
      MyUserDetailsService userDetailsService,
      PasswordEncoder bCryptPasswordEncoder)
      throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
        AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(userDetailsService)
        .passwordEncoder(bCryptPasswordEncoder);

    return authenticationManagerBuilder.build();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable());

//    http.authorizeHttpRequests((authorizeHttpRequests) ->
//        authorizeHttpRequests.anyRequest().authenticated()
//    );

    http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    return http.build();
  }
}