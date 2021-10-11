package com.ecommerce.wishlist.adapter.out.mongodb.wish;

import com.ecommerce.wishlist.adapter.out.WishRepository;
import com.ecommerce.wishlist.domain.Wish;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  @Override
  public void delete(String wishId) {
    mongodbWishRepository.deleteById(wishId);
  }

  @Override
  public List<Wish> findByCustomerId(String customerId) {
    var result = mongodbWishRepository.findByCustomerId(customerId);
    return result.stream().map(MongodbWishMapper::toDomain).collect(Collectors.toList());
  }

  @Override
  public Optional<Wish> findOneByCustomerIdAndProductId(String customerId, String productId) {
    Optional<WishSchema> result =
        mongodbWishRepository.findOneByCustomerIdAndProductId(customerId, productId);
    return result.map(MongodbWishMapper::toDomain);
  }
}
