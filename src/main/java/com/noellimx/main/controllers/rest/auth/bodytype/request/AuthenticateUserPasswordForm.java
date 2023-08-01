package com.noellimx.main.controllers.rest.auth.bodytype.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticateUserPasswordForm {

  @JsonProperty("username")
  public String username;

  @JsonProperty("password")
  public String password;

  @Override
  public String toString() {
    return "AuthenticateUserPasswordForm{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
