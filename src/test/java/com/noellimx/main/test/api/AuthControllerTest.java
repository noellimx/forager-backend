package com.noellimx.main.test.api;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class AuthControllerTest {


  private final WebTestClient webTestClient;

  SerialGenerator serialGenerator = new SerialGenerator();

  @Autowired
  public AuthControllerTest(
      WebTestClient webTestClient) {
    this.webTestClient = webTestClient;
  }

  @Test
  @Order(1)
  public void ShouldReturnOK_RegisterOnce() {

    String requestBody = "{ \\\"username\\\" : \\\"testuser1\\\", \\\"password\\\": \\\"password\\\"}\n";

    Map<String, String> bodyMap = new HashMap<>();
    bodyMap.put("username", "testuser001" + serialGenerator.next());
    bodyMap.put("password", "pwtestuser001");

    this.webTestClient
        .post()
        .uri("/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(bodyMap))
        .exchange()
        .expectStatus().is2xxSuccessful();
  }

  @Test
  @Order(2)
  public void ShouldReturn4XX_RegisterTwice() {

    String username = "testuser" + serialGenerator.next();
    Map<String, String> bodyMap = new HashMap<>();
    bodyMap.put("username", username);
    bodyMap.put("password", "pwtestuser001");

    this.webTestClient
        .post()
        .uri("/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(bodyMap))
        .exchange()
        .expectStatus().is2xxSuccessful();

    this.webTestClient
        .post()
        .uri("/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(bodyMap))
        .exchange()
        .expectStatus().is5xxServerError();
  }

  @Test
  @Order(3)
  public void ShouldReturnOK_WithToken_RegisterAndLogin() {

    String username = "testuserreglogin" + serialGenerator.next();

    String password = "pwtestuser001";
    Map<String, String> bodyMap = new HashMap<>();
    bodyMap.put("username", username);
    bodyMap.put("password", password);

    System.out.println("ShouldReturnOK_WithToken_RegisterAndLogin " + bodyMap);

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
  }
}
