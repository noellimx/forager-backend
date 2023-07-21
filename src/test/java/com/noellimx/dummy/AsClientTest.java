package com.noellimx.dummy;

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
    this.webTestClient
        .get()
        .uri("/api/users")
        .exchange()
        .expectStatus().is4xxClientError();
  }

}
