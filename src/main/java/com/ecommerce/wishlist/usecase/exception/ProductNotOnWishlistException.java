package com.ecommerce.wishlist.usecase.exception;

public class ProductNotOnWishlistException extends BusinessRuleException {

  public ProductNotOnWishlistException() {
    super("Exception.ProductNotOnWishlistException");
  }
}
