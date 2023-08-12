package com.noellimx.main.test.service.integration;

import com.noellimx.main.entity.YoutubeReference;
import com.noellimx.main.service.JwtAuthService;
import com.noellimx.main.service.MyUserDetailsService;
import com.noellimx.main.service.app.FoodEstablishmentService;
import com.noellimx.main.service.app.YoutubeReferenceService;
import com.noellimx.main.test.utils.SerialGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


@SpringBootTest
public class CreatingYoutubeReferenceTest {

  MyUserDetailsService userService;

  JwtAuthService jwtAuthService;
  FoodEstablishmentService outletService;

  YoutubeReferenceService youtubeReference;

  SerialGenerator serialGenerator = new SerialGenerator();


  @Autowired
  public CreatingYoutubeReferenceTest(MyUserDetailsService userService,
      FoodEstablishmentService outletService, YoutubeReferenceService youtubeReference,
      JwtAuthService jwtAuthService) {
    this.userService = userService;
    this.outletService = outletService;
    this.youtubeReference = youtubeReference;

    this.jwtAuthService = jwtAuthService;
  }

  @Test
  public void TestCreateOutlet() {
    String username = ("userreglogin" + serialGenerator.next()).substring(0, 20);
    String password = "pwtestuser001";
    UserDetails user = User.builder().username(username).password(password).build();
    this.userService.createUser(user);
    String token = this.jwtAuthService.generateToken(user);

    outletService.create("LICENCSE", "123456", "ABC");

    YoutubeReference ref = youtubeReference.create("v123", "LICENCSE", "123", username);
    System.out.println("testcreateoutlet " + ref);
  }
}
