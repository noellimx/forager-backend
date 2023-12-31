package com.noellimx.main.exception.controller;

public class NotImplementedException extends RuntimeException {

  public NotImplementedException(String message) {
    super(message);
  }

  public NotImplementedException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotImplementedException(Throwable cause) {
    super(cause);
  }
}
