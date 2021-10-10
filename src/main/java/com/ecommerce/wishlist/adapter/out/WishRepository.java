package com.ecommerce.wishlist.adapter.out;

import com.ecommerce.wishlist.domain.Wish;

public interface WishRepository {

  Wish save(Wish wish);

  Integer countByCustomerId(String customerId);
}
