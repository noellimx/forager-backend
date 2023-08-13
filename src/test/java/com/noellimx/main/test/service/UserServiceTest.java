package com.noellimx.main.test.service;


import com.noellimx.main.exception.controller.NotFoundException;
import com.noellimx.main.service.MyUserDetailsService;
import com.noellimx.main.test.utils.SerialGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserServiceTest {

  final MyUserDetailsService service;

  final SerialGenerator serialGenerator = new SerialGenerator();
  private static final String testKey = "fb8e20fc2e4c3f248c60c39bd652f3c1347298bb977b8b4d5903b85055620603";

  @Autowired
  public UserServiceTest(MyUserDetailsService service) {
    this.service = service;
  }

  @Test
  void UserValidation() {

    String username = "testuser1" + serialGenerator.next();
    String password = "passwordUserValidation";
    UserDetails userDetails = User.builder().username(username).password(password).build();

    // Before creating
    Assertions.assertThrows(UsernameNotFoundException.class,
        () -> this.service.loadUserByUsername(username));

    Assertions.assertThrows(NotFoundException.class,
        () -> this.service.authenticateUser(userDetails));

    // After creating
    this.service.createUser(userDetails);

    UserDetails gotAfterCreate = this.service.loadUserByUsername(username);
    Assertions.assertNotNull(gotAfterCreate);

    UserDetails gotAfterAuthenticate = this.service.authenticateUser(userDetails);

    Assertions.assertNotNull(gotAfterAuthenticate);
  }
}
