package com.noellimx.main.controllers.rest.foodestablishment;


import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food-establishment")
public class FoodEstablishmentController {


  @Autowired
  public FoodEstablishmentController() {

  }

  @PostMapping("/")
  public ResponseEntity create(Principal p, Authentication auth) {

    return ResponseEntity.status(501).build();
  }


}
