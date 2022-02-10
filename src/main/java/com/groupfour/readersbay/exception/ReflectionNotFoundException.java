package com.groupfour.readersbay.exception;

public class ReflectionNotFoundException extends Exception {
  public ReflectionNotFoundException() {
  }

  public ReflectionNotFoundException(String message) {
    super(message);
  }

  public ReflectionNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ReflectionNotFoundException(Throwable cause) {
    super(cause);
  }

  public ReflectionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
