package com.ecommerce.wishlist.usecase;

import com.ecommerce.wishlist.adapter.out.WishRepository;
import com.ecommerce.wishlist.domain.Wish;
import com.ecommerce.wishlist.usecase.exception.ProductNotOnWishlistException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class FindWish {

  private final WishRepository wishRepository;

  public Wish execute(final String customerId, final String productId) {
    return wishRepository
        .findOneByCustomerIdAndProductId(customerId, productId)
        .orElseThrow(ProductNotOnWishlistException::new);
  }
}
