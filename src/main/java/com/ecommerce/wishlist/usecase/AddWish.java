package com.ecommerce.wishlist.usecase;

import com.ecommerce.wishlist.adapter.out.WishRepository;
import com.ecommerce.wishlist.domain.Wish;
import com.ecommerce.wishlist.usecase.exception.WishlistLimitReachedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AddWish {

  private static final int WISHLIST_LIMIT = 20;
  private final WishRepository wishRepository;

  public Wish execute(final Wish wish) {
    checkCustomerWishlistLimit(wish.getCustomerId());
    return wishRepository.save(wish);
  }

  private void checkCustomerWishlistLimit(final String customerId) {
    var wishlist = wishRepository.countByCustomerId(customerId);
    if (wishlist == WISHLIST_LIMIT) {
      throw new WishlistLimitReachedException(WISHLIST_LIMIT);
    }
  }
}
