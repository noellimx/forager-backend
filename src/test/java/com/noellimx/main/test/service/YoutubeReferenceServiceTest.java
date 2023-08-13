package com.noellimx.main.test.service;

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
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class YoutubeReferenceServiceTest {

  final YoutubeReferenceService service;

  @Autowired
  public YoutubeReferenceServiceTest(YoutubeReferenceService service) {
    this.service = service;
  }

  @Test
  @Order(1)
  public void ShouldCreateReferencesForVideoOK_TestSet() {

    service.create("Uzl77r-lR2M", "E78125L001", "1:30", "sometestuser");

    // [X] duplicate reference for video
    Assertions.assertThrows(DataIntegrityViolationException.class,
        () -> service.create("Uzl77r-lR2M", "E78125L001", "1:30", "sometestuser"));

    // second reference for video
    service.create("Uzl77r-lR2M", "E78125L002", "1:30", "sometestuser");

    // third reference without username
    service.create("Uzl77r-lR2M", "E78125L003", "1:30", "");

    Assertions.assertEquals(1, service.findAllByLicenseNo("E78125L001").size());
    Assertions.assertEquals(3, service.findAllByVideoId("Uzl77r-lR2M").size());

    // Another video reference for establishment E78125L001.
    service.create("Uzl77r-1234", "E78125L001", "12:30", "sometestuser");

    Assertions.assertEquals(2, service.findAllByLicenseNo("E78125L001").size());
  }
}
