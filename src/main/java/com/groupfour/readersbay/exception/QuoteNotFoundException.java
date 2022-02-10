package com.groupfour.readersbay.exception;

public class QuoteNotFoundException extends Exception {
  public QuoteNotFoundException() {
  }

  public QuoteNotFoundException(String message) {
    super(message);
  }

  public QuoteNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public QuoteNotFoundException(Throwable cause) {
    super(cause);
  }

  public QuoteNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
