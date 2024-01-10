package com.auto.companion.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class GlobalException extends RuntimeException {

  protected final HttpStatus httpStatus;
  protected final String errorMessage;
}
