package com.noellimx.main.controllers.rest.auth.bodytype.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterForm {

  @JsonProperty("username")
  public String username;

  @JsonProperty("password")
  public String password;

  @Override
  public String toString() {
    return "RegisterForm{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
