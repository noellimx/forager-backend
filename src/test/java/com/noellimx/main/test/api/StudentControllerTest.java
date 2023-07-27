package com.noellimx.main.test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

  @Autowired
  private WebTestClient webTestClient;


  @Test
  public void ShouldReturnStudents() {
    this.webTestClient
        .get()
        .uri("/students/")
        .exchange()
        .expectStatus().isOk().expectBody().consumeWith(response -> {
          String body = new String(response.getResponseBody(), StandardCharsets.UTF_8);
          assertEquals(body,
              "[{\"id\":null,\"firstName\":\"abel\",\"lastName\":\"lee\",\"email\":\"@\"},{\"id\":null,\"firstName\":\"alex\",\"lastName\":\"poh\",\"email\":\"@\"},{\"id\":null,\"firstName\":\"xavier\",\"lastName\":\"tan\",\"email\":\"@\"}]");
        });
  }

}
