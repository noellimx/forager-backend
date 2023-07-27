package com.noellimx.main.test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
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

public class StudentControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  @Order(1)
  public void ShouldReturnOK_Student_Many() {
    this.webTestClient
        .get()
        .uri("/students/")
        .exchange()
        .expectStatus().isOk().expectBody().consumeWith(response -> {
          String body = new String(response.getResponseBody(), StandardCharsets.UTF_8);
          assertEquals(body,
              "[{\"id\":1,\"firstName\":\"FirstSeedStudent\",\"lastName\":\"Lim\",\"email\":\"seed@email.com\"},{\"id\":2,\"firstName\":\"SecondSeedStudent\",\"lastName\":\"Lim\",\"email\":\"seed@email.com\"}]");
        });
  }

  // GET /students/{id}
  @Test
  @Order(2)
  public void ShouldGetReturnOK_Student_One() {

    class TestCase {

      Integer id;
      String wantResponseBody;

      public TestCase(Integer id, String wantResponseBody) {
        this.id = id;
        this.wantResponseBody = wantResponseBody;
      }
    }

    List<TestCase> testCases = Arrays.asList(new TestCase(1,
            "{\"id\":1,\"firstName\":\"FirstSeedStudent\",\"lastName\":\"Lim\",\"email\":\"seed@email.com\"}"),
        new TestCase(2,
            "{\"id\":2,\"firstName\":\"SecondSeedStudent\",\"lastName\":\"Lim\",\"email\":\"seed@email.com\"}")
    );

    for (TestCase tc : testCases) {
      this.webTestClient
          .get()
          .uri("/students/" + tc.id)
          .exchange()
          .expectStatus().isOk().expectBody().consumeWith(response -> {
            String body = new String(response.getResponseBody(), StandardCharsets.UTF_8);

            System.out.println("--ds-");
            System.out.println(body);
            assertEquals(body,
                tc.wantResponseBody);
          });
    }
  }


  @Test
  @Order(2)
  public void ShouldGetReturnNotFound_NonExistentStudent_One() {
    this.webTestClient
        .get()
        .uri("/students/" + 9899)
        .exchange()
        .expectStatus().isNotFound().expectBody().jsonPath("$.message")
        .isEqualTo("Resource: Student not found")
        .jsonPath("$.status")
        .isEqualTo(404);
  }


  // PUT /students/
  @Test
  @Order(3)
  public void ShouldUpdate_Student_one() {

    class TestCase {

      String wantResponseBody;
      String requestBody;

      public TestCase(String wantResponseBody, String requestBody) {
        this.requestBody = requestBody;
        this.wantResponseBody = wantResponseBody;
      }
    }

    TestCase tc = new TestCase(
        "{\"id\":1,\"firstName\":\"updatedFirstName\",\"lastName\":\"updatedLastName\",\"email\":\"up@date.com\"}",
        "{ \"id\": \"1\", \"lastName\": \"updatedLastName\",\"firstName\":\"updatedFirstName\",\"email\":\"up@date.com\"}\n");

    this.webTestClient
        .put()
        .uri("/students/")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(tc.requestBody))
        .exchange()
        .expectStatus().isOk().expectBody().consumeWith(response -> {
          String body = new String(response.getResponseBody(), StandardCharsets.UTF_8);
          assertEquals(body,
              tc.wantResponseBody);
        });
  }

  // PUT /students/
  @Test
  @Order(4)
  public void ShouldDelete_Student_one() {

    this.webTestClient
        .delete()
        .uri("/students/" + 1)
        .exchange()
        .expectStatus().isOk();

    this.webTestClient
        .delete()
        .uri("/students/" + 1)
        .exchange().expectBody()
        .jsonPath("$.message")
        .isEqualTo("Unable to delete null. Resource not found")
        .jsonPath("$.status")
        .isEqualTo(404);
  }


  @Test
  @Order(5)

  public void ShouldReturnError_Student_InvalidPath() {
    this.webTestClient
        .get()
        .uri("/students/invalid")
        .exchange()
        .expectStatus().is5xxServerError()
        .expectBody()
        .jsonPath("$.message")
        .isEqualTo("oh no")
        .jsonPath("$.status")
        .isEqualTo(501);
  }
}
