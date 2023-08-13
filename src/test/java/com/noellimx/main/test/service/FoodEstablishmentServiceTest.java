package com.noellimx.main.test.service;

import com.noellimx.main.entity.FoodEstablishment;
import com.noellimx.main.service.app.FoodEstablishmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class FoodEstablishmentServiceTest {

  final FoodEstablishmentService service;

  @Autowired
  public FoodEstablishmentServiceTest(FoodEstablishmentService FoodEstablishmentService) {
    this.service = FoodEstablishmentService;
  }

  @Test
  @Order(1)
  public void ShouldCreate() {

    service.create("E78125L001", "", "");

    FoodEstablishment est = service.getByLicenseNo("E78125L001");

    Assertions.assertNotNull(est);
    Assertions.assertThrows(DataIntegrityViolationException.class,
        () -> this.service.create("E78125L001", "", ""));
  }


}
