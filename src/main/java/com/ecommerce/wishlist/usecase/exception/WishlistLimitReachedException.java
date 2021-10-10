package com.ecommerce.wishlist.usecase.exception;

public class WishlistLimitReachedException extends BusinessRuleException {

  public WishlistLimitReachedException(int limit) {
    super("Exception.WishlistLimitReachedException", String.valueOf(limit));
  }
}
