package com.noellimx.main.test.service;

import com.noellimx.main.service.app.FoodEstablishmentService;
import com.noellimx.main.service.app.YoutubeReferenceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class YoutubeReferenceServiceTest {

  final YoutubeReferenceService service;


  final FoodEstablishmentService foodEstablishmentService;

  @Autowired
  public YoutubeReferenceServiceTest(YoutubeReferenceService service,
      FoodEstablishmentService foodEstablishmentService) {
    this.service = service;

    this.foodEstablishmentService = foodEstablishmentService;
  }

  @Test
  @Order(1)
  public void ShouldCreateReferencesForVideoOK_TestSet() {

    String sfa1 = "E78125L991";
    String sfa2 = "E78125L992";
    String sfa3 = "E78125L993";

    foodEstablishmentService.create(sfa1, "", "");
    foodEstablishmentService.create(sfa2, "", "");
    foodEstablishmentService.create(sfa3, "", "");
    service.create("Uzl77r-lR2M", sfa1, "1:30", "sometestuser");
    service.create("Uzl77r-lR2M", sfa2, "1:30", "sometestuser");
    service.create("Uzl77r-lR2M", sfa3, "1:30", "sometestuser");

//
    Assertions.assertEquals(1, service.findAllByLicenseNo(sfa1).size());
//    Assertions.assertEquals(3, service.findAllByVideoId("Uzl77r-lR2M").size());
//
//    // Another video reference for establishment E78125L001.
//    service.create("Uzl77r-1234", "E78125L001", "12:30", "sometestuser");
//
//    Assertions.assertEquals(2, service.findAllByLicenseNo("E78125L001").size());
  }
}
