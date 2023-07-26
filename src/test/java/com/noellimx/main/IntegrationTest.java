package com.noellimx.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.noellimx.main.test.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import({TestSecurityConfig.class})
public class IntegrationTest {


  @Autowired
  private WebTestClient webTestClient;
  @Autowired
  private TestSecurityConfig testSecurityConfig;

  @Test
  public void contextLoads() {

  }

  @Test
  public void ShouldReturnOK() {
    this.webTestClient
        .get()
        .uri("/")
        .headers(headers -> headers.setBasicAuth("user", testSecurityConfig.getUserPassword()))

        .exchange()
        .expectStatus().isOk().returnResult(String.class).getResponseBody()
        .subscribe(responseBody -> {
          // Process the response body here
          System.out.println("Response Body: " + responseBody);
        });

    this.webTestClient
        .get()
        .uri("/defense")
        .headers(headers -> headers.setBasicAuth("user", testSecurityConfig.getUserPassword()))
        .exchange()
        .expectStatus().isOk().returnResult(String.class).getResponseBody()
        .subscribe(responseBody -> {
          assertEquals(responseBody, "-2");
        });
  }
}
