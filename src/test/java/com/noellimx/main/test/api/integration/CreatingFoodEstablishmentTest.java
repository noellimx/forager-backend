package com.noellimx.main.test.api.integration;


import com.noellimx.main.controllers.rest.auth.bodytype.response.AuthenticatedResponse;
import com.noellimx.main.test.utils.SerialGenerator;
import java.util.HashMap;
import java.util.Map;
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


  private WebTestClient webTestClient;

  private static String token;

  SerialGenerator serialGenerator = new SerialGenerator();

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
        .uri("/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(bodyMap))
        .exchange()
        .expectStatus().is2xxSuccessful();

    AuthenticatedResponse response = this.webTestClient
        .post()
        .uri("/auth/authenticate")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(bodyMap))
        .exchange()
        .expectStatus().is2xxSuccessful().expectBody(AuthenticatedResponse.class)
        .returnResult()
        .getResponseBody();
    System.out.println("response obtained is " + response);

    this.token = response.token;
  }


  @Test
  @Order(2)
  public void ShouldReturnNotImplemented_GivenToken_WhenCreatingFoodEstablishment() {

    System.out.println("token currently is " + this.token);
    Map<String, String> bodyMap = new HashMap<>();

    this.webTestClient
        .post()
        .uri("/food-establishment/").headers(h -> h.set("Authorization", "Bearer " + this.token))
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(bodyMap))
        .exchange()
        .expectStatus().isEqualTo(HttpStatus.NOT_IMPLEMENTED);
  }
}
