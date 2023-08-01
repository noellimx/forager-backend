package com.noellimx.main.test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {


  @Autowired
  private WebTestClient webTestClient;

  @Test
  public void contextLoads() {

  }

  @Test
  public void ShouldReturnOK() {
    this.webTestClient
        .get()
        .uri("/")

        .exchange()
        .expectStatus().isOk().returnResult(String.class).getResponseBody()
        .subscribe(responseBody -> {
          System.out.println("Response Body: " + responseBody);
        });

    this.webTestClient
        .get()
        .uri("/defense")
        .exchange()
        .expectStatus().isOk().returnResult(String.class).getResponseBody()
        .subscribe(responseBody -> {
          assertEquals(responseBody, "-2");
        });
  }
}
