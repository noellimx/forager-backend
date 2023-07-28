package com.noellimx.main.test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.noellimx.main.configuration.BasicSecurityProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import({BasicSecurityProfile.class})

public class IntegrationTest {


  @Autowired
  private WebTestClient webTestClient;
  @Autowired
  private BasicSecurityProfile basicSecurityProfile;

  @Test
  public void contextLoads() {

  }

  @Test
  public void ShouldReturnOK() {
    this.webTestClient
        .get()
        .uri("/")
//        .headers(headers -> headers.setBasicAuth("user", testSecurityConfig.getUserPassword()))
        .headers(headers -> headers.setBasicAuth(basicSecurityProfile.getUsername(),
            basicSecurityProfile.getPassword()))
        .exchange()
        .expectStatus().isOk().returnResult(String.class).getResponseBody()
        .subscribe(responseBody -> {
          // Process the response body here
          System.out.println("Response Body: " + responseBody);
        });

    this.webTestClient
        .get()
        .uri("/defense")
        .headers(headers -> headers.setBasicAuth(basicSecurityProfile.getUsername(),
            basicSecurityProfile.getPassword()))
//        .headers(headers -> headers.setBasicAuth("user", testSecurityConfig.getUserPassword()))
        .exchange()
        .expectStatus().isOk().returnResult(String.class).getResponseBody()
        .subscribe(responseBody -> {
          assertEquals(responseBody, "-2");
        });
  }
}
