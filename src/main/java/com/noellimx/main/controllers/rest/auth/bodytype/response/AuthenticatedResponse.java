package com.noellimx.main.controllers.rest.auth.bodytype.response;


import com.fasterxml.jackson.annotation.JsonProperty;


public class AuthenticatedResponse {


  @JsonProperty("token")
  public String token;

  public AuthenticatedResponse(String token) {
    this.token = token;
  }

  private AuthenticatedResponse() {
  }


  @Override
  public String toString() {
    return "AuthenticatedResponse{" +
        "token='" + token + '\'' +
        '}';
  }
}
