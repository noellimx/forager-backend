package com.noellimx.main.controllers.rest;


import com.noellimx.main.exception.controller.NotFoundException;
import com.noellimx.main.exception.controller.NotImplementedException;
import com.noellimx.main.exception.controller.errorResponse.StudentErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {StudentController.class})
public class StudentControllerAdvice {

  @ExceptionHandler
  public ResponseEntity<StudentErrorResponse> handleException501(NotImplementedException exc) {
    StudentErrorResponse error = new StudentErrorResponse(501, exc.getMessage(),
        System.currentTimeMillis());

    return new ResponseEntity<>(error, HttpStatus.NOT_IMPLEMENTED);
  }

  @ExceptionHandler
  public ResponseEntity<StudentErrorResponse> handleException404(NotFoundException exc) {
    StudentErrorResponse error = new StudentErrorResponse(404, exc.getMessage(),
        System.currentTimeMillis());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }
}
