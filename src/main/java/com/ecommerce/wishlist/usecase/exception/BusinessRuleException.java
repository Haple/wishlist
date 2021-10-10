package com.ecommerce.wishlist.usecase.exception;

import lombok.Getter;

@Getter
public class BusinessRuleException extends RuntimeException {

  private final String message;
  private final String[] args;

  public BusinessRuleException(String message, String... args) {
    super(message);
    this.message = message;
    this.args = args;
  }
}
