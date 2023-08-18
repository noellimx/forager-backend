package com.noellimx.main.test.api.integration;


import com.noellimx.main.controllers.rest.auth.bodytype.response.AuthenticatedResponse;
import com.noellimx.main.controllers.rest.foodestablishment.bodytype.request.FoodEstablishmentForm;
import com.noellimx.main.test.utils.SerialGenerator;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
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
public class CreatingFoodEstablishmentTest {

  private final WebTestClient webTestClient;

  private static String token;

  final SerialGenerator serialGenerator = new SerialGenerator();

  @Autowired
  public CreatingFoodEstablishmentTest(
      WebTestClient webTestClient) {
    this.webTestClient = webTestClient;
  }


  @Test
  @Order(1)
  public void ShouldReturnOK_WithToken_RegisterAndLogin() {

    String username = "foodcreator" + serialGenerator.next();
    String password = "pwtestuser001";

    Map<String, String> bodyMap = new HashMap<>();
    bodyMap.put("username", username);
    bodyMap.put("password", password);

    this.webTestClient
        .post()
        .uri("/api/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(bodyMap))
        .exchange()
        .expectStatus().is2xxSuccessful();

    AuthenticatedResponse response = this.webTestClient
        .post()
        .uri("/api/auth/authenticate")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(bodyMap))
        .exchange()
        .expectStatus().is2xxSuccessful().expectBody(AuthenticatedResponse.class)
        .returnResult()
        .getResponseBody();

    token = response.token;
  }


  @Test
  @Order(2)
  public void ShouldReturnOK_GivenToken_WhenCreatingFoodEstablishment() {
    Map<String, String> bodyMap = new HashMap<>();

    bodyMap.put("sfa_license_no", "E78127L003");
    bodyMap.put("postal_code_official", "");
    bodyMap.put("business_name", "DE FU SEAFOOD (NUC 59) PTE LTD");

    FoodEstablishmentForm form = this.webTestClient
        .post()
        .uri("/api/food-establishment/").headers(h -> h.set("Authorization", "Bearer " + token))
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(bodyMap))
        .exchange()
        .expectStatus().isEqualTo(HttpStatus.NOT_IMPLEMENTED)
        .expectBody(FoodEstablishmentForm.class)
        .returnResult()
        .getResponseBody();

    Assertions.assertEquals("E78127L003", form.sfa_license_no);

  }
}
