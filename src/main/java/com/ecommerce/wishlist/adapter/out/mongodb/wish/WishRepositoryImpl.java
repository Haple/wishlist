package com.ecommerce.wishlist.adapter.out.mongodb.wish;

import com.ecommerce.wishlist.adapter.out.WishRepository;
import com.ecommerce.wishlist.domain.Wish;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.ecommerce.wishlist.adapter.out.mongodb.wish.MongodbWishMapper.toDomain;
import static com.ecommerce.wishlist.adapter.out.mongodb.wish.MongodbWishMapper.toSchema;

@Repository
@AllArgsConstructor
public class WishRepositoryImpl implements WishRepository {

  private final MongodbWishRepository mongodbWishRepository;

  @Override
  public Wish save(Wish wish) {
    var result = mongodbWishRepository.save(toSchema(wish));
    return toDomain(result);
  }

  @Override
  public Integer countByCustomerId(String customerId) {
    return mongodbWishRepository.countByCustomerId(customerId);
  }
}
