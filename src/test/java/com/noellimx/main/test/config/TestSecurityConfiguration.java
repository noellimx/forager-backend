//package com.noellimx.main.test.config;
//
//import com.noellimx.main.configuration.BasicSecurityProfile;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@TestConfiguration
//@Component
//public class TestSecurityConfiguration {
//
//
//  @Autowired
//  BasicSecurityProfile basicSecurityProfile;
//
//  public static PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
////  @Bean
////  @Primary
////  public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
////
////    UserDetails testUser = User.builder().username(basicSecurityProfile.getUsername())
////        .password(passwordEncoder().encode(basicSecurityProfile.getPassword()))
////        .roles("USER", "ADMIN")
////        .build();
////
////    return new InMemoryUserDetailsManager(testUser);
////  }
//
//
//}