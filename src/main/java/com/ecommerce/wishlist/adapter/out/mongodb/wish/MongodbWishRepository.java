package com.ecommerce.wishlist.adapter.out.mongodb.wish;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongodbWishRepository extends MongoRepository<WishSchema, String> {

  int countByCustomerId(String customerId);
}
