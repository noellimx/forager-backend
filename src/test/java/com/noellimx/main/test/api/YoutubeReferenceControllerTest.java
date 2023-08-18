package com.noellimx.main.test.api;

import com.noellimx.main.entity.YoutubeReference;
import com.noellimx.main.service.app.YoutubeReferenceService;
import com.noellimx.main.test.utils.SerialGenerator;
import com.noellimx.main.test.utils.authenticate.Authenticate;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class YoutubeReferenceControllerTest {

  private final WebTestClient webTestClient;


  final SerialGenerator serialGenerator = new SerialGenerator();

  @Autowired
  public YoutubeReferenceControllerTest(WebTestClient webTestClient, YoutubeReferenceService ss) {
    this.webTestClient = webTestClient;

  }

  @Test
  @Order(1)
  public void ShouldReturnOK_Items_Many() {

    String username = ("userreglogin" + serialGenerator.next()).substring(0, 20);

    String password = "pwtestuser001";

    String token = Authenticate.getToken(this.webTestClient, username, password);
    Map<String, String> bodyMap = new HashMap<>();

    bodyMap.put("sfa_license_no", "LLLL003");
    bodyMap.put("video_id", "v123");
    bodyMap.put("timestamp", "t123");

    YoutubeReference ref = this.webTestClient
        .post()
        .uri("/api/reference/youtube/").headers(h -> h.set("Authorization", "Bearer " + token))
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(bodyMap))
        .exchange()
        .expectStatus().isEqualTo(HttpStatus.OK)
        .expectBody(YoutubeReference.class)
        .returnResult()
        .getResponseBody();

    Assertions.assertEquals("LLLL003", ref.getSfaLicenseNo());
    Assertions.assertEquals("v123", ref.getVideoId());
    Assertions.assertEquals("t123", ref.getTimestamp());
    Assertions.assertEquals(username, ref.getCreatorName());

  }

}
