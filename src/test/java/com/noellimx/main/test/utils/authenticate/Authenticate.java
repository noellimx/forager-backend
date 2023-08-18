package com.noellimx.main.test.utils.authenticate;

import com.noellimx.main.controllers.rest.auth.bodytype.response.AuthenticatedResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

public class Authenticate {

  public static String getToken(WebTestClient webTestClient, String username, String password) {

    Map<String, String> bodyMap = new HashMap<>();
    bodyMap.put("username", username);
    bodyMap.put("password", password);

    webTestClient
        .post()
        .uri("/api/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(bodyMap))
        .exchange()
        .expectStatus().is2xxSuccessful();

    AuthenticatedResponse response = webTestClient
        .post()
        .uri("/api/auth/authenticate")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(bodyMap))
        .exchange()
        .expectStatus().is2xxSuccessful().expectBody(AuthenticatedResponse.class)
        .returnResult()
        .getResponseBody();

    return response.token;
  }
}
