package com.ecommerce.wishlist.adapter.out.mongodb.wish;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MongodbWishRepository extends MongoRepository<WishSchema, String> {

  int countByCustomerId(String customerId);

  List<WishSchema> findByCustomerId(String customerId);

  Optional<WishSchema> findOneByCustomerIdAndProductId(String customerId, String productId);
}
