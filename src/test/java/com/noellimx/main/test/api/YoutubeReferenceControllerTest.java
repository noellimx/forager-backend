package com.noellimx.main.test.api;

import com.noellimx.main.service.app.YoutubeReferenceService;
import com.noellimx.main.test.utils.SerialGenerator;
import com.noellimx.main.test.utils.authenticate.Authenticate;
import java.util.HashMap;
import java.util.Map;
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
  public void ShouldReturnOK_CreateItemOne() {

    String username = ("userreglogin" + serialGenerator.next()).substring(0, 20);

    String password = "pwtestuser001";

    String token = Authenticate.getToken(this.webTestClient, username, password);
    Map<String, String> bodyMap = new HashMap<>();

    bodyMap.put("sfa_license_no", "LLLL003");
    bodyMap.put("video_id", "v123");
    bodyMap.put("timestamp", "t123");

    this.webTestClient
        .post()
        .uri("/api/reference/youtube/").headers(h -> h.set("Authorization", "Bearer " + token))
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(bodyMap))
        .exchange()

        .expectAll(
            spec -> spec.expectStatus().isEqualTo(HttpStatus.OK),
            spec -> spec.expectBody(String.class).isEqualTo(
                "{\"video_id\":\"v123\",\"timestamp\":\"t123\",\"food_establishment\":{\"sfa_license_no\":\"LLLL003\"}}")
        /* DO NOT DELETE: for diagnostics */
        // ,
        // spec -> {
        // YoutubeReferenceResponse ref =
        // spec.expectBody(YoutubeReferenceResponse.class)
        // .returnResult().getResponseBody();
        // Assertions.assertNotNull(ref);
        // Assertions.assertEquals("LLLL003",
        // ref.getResponseFoodEstablishment().getSfaLicenseNo());
        // Assertions.assertEquals("t123", ref.getTimestamp());
        // }
        );
  }

  @Test
  @Order(1)
  public void ShouldReturnOK_GetAll() {

    String username = ("userreglogin" + serialGenerator.next()).substring(0, 20);
    String password = "pwtestuser001";

    String token = Authenticate.getToken(this.webTestClient, username, password);
    Map<String, String> bodyMap = new HashMap<>();

    bodyMap.put("sfa_license_no", "123");
    bodyMap.put("video_id", "123");
    bodyMap.put("timestamp", "1h");

    this.webTestClient
        .post()
        .uri("/api/reference/youtube/").headers(h -> h.set("Authorization", "Bearer " + token))
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(bodyMap))
        .exchange()

        .expectAll(
            spec -> spec.expectStatus().isEqualTo(HttpStatus.OK),
            spec -> spec.expectBody(String.class).isEqualTo(
                "{\"video_id\":\"123\",\"timestamp\":\"1h\",\"food_establishment\":{\"sfa_license_no\":\"123\"}}")
        /* DO NOT DELETE: for diagnostics */
        // ,
        // spec -> {
        // YoutubeReferenceResponse ref =
        // spec.expectBody(YoutubeReferenceResponse.class)
        // .returnResult().getResponseBody();
        // Assertions.assertNotNull(ref);
        // Assertions.assertEquals("LLLL003",
        // ref.getResponseFoodEstablishment().getSfaLicenseNo());
        // Assertions.assertEquals("t123", ref.getTimestamp());
        // }
        );

    this.webTestClient.get()
        .uri("/api/reference/youtube/all")
        .exchange()
        .expectStatus().isOk().expectBody(String.class).isEqualTo(
            "{\"data\":[{\"video_id\":\"v123\",\"timestamp\":\"t123\",\"food_establishment\":{\"sfa_license_no\":\"LLLL003\"}},{\"video_id\":\"123\",\"timestamp\":\"1h\",\"food_establishment\":{\"sfa_license_no\":\"123\"}}],\"message\":\"\"}");

    // .isEqualTo("Resource: Student not found")
    // .jsonPath("$.status")
    // .isEqualTo(404);
  }

}
