package com.ecommerce.wishlist.adapter.out;

import com.ecommerce.wishlist.domain.Wish;

import java.util.List;
import java.util.Optional;

public interface WishRepository {

  Wish save(Wish wish);

  Integer countByCustomerId(String customerId);

  void delete(String wishId);

  List<Wish> findByCustomerId(String customerId);

  Optional<Wish> findOneByCustomerIdAndProductId(String customerId, String productId);
}
