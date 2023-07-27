package com.noellimx.main.test.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AsClientTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  public void contextLoads() {

  }

  @Test
  public void ShouldReturn404() {
    this.webTestClient
        .get()
        .uri("/some/unknown/api")
        .exchange()
        .expectStatus().is4xxClientError();
  }
}
