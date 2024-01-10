package com.auto.companion.domain.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(value = { GlobalException.class })
  @ResponseBody
  public ResponseEntity<RestException> handleException(GlobalException globalException) {
    return ResponseEntity
        .status(globalException.getHttpStatus())
        .body(new RestException(globalException.errorMessage));
  }
}
