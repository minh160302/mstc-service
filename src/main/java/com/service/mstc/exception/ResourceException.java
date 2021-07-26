package com.service.mstc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.File;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceException extends RuntimeException{
  public ResourceException(String message) {
    super(message);
  }
  public ResourceException(String message, File file) {
    super(message);
    file.delete();
  }

}
