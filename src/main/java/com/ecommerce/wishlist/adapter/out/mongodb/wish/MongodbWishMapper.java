package com.ecommerce.wishlist.adapter.out.mongodb.wish;

import com.ecommerce.wishlist.domain.Wish;

import java.util.Optional;

class MongodbWishMapper {

  static Wish toDomain(WishSchema wishSchema) {
    return Optional.ofNullable(wishSchema)
        .map(
            schema ->
                Wish.builder()
                    .id(schema.getId())
                    .customerId(schema.getCustomerId())
                    .productId(schema.getProductId())
                    .createdAt(schema.getCreatedAt())
                    .updatedAt(schema.getUpdatedAt())
                    .build())
        .orElse(null);
  }

  static WishSchema toSchema(Wish wish) {
    return Optional.ofNullable(wish)
        .map(
            domain ->
                WishSchema.builder()
                    .id(domain.getId())
                    .customerId(domain.getCustomerId())
                    .productId(domain.getProductId())
                    .createdAt(domain.getCreatedAt())
                    .updatedAt(domain.getUpdatedAt())
                    .build())
        .orElse(null);
  }
}
