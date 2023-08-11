package com.noellimx.main.controllers.rest.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonResponse<T> {

  @JsonProperty("data")
  public T data;


  @JsonProperty("message")
  public String message;

  public JsonResponse(T data, String message) {
    this.data = data;
    this.message = message;
  }

  public JsonResponse() {
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "JsonResponse{" +
        "data=" + data +
        ", message='" + message + '\'' +
        '}';
  }
}
