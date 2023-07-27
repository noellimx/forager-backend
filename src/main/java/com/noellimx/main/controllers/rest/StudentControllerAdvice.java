package com.noellimx.main.controllers.rest;


import com.noellimx.main.exception.controller.NotImplementedException;
import com.noellimx.main.exception.controller.errorResponse.StudentErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {StudentController.class})
public class StudentControllerAdvice {

  @ExceptionHandler
  public ResponseEntity<StudentErrorResponse> handleException(NotImplementedException exc) {

    StudentErrorResponse error = new StudentErrorResponse(501, exc.getMessage(),
        System.currentTimeMillis());

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

  }
}
