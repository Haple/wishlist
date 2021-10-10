package com.ecommerce.wishlist.adapter.in.http.controllers.wish;

import com.ecommerce.wishlist.adapter.in.http.controllers.wish.request.CreateWishRequest;
import com.ecommerce.wishlist.adapter.in.http.controllers.wish.response.WishResponse;
import com.ecommerce.wishlist.domain.Wish;

import java.util.Optional;

class ControllerWishMapper {

  static Wish toDomain(CreateWishRequest wishRequest) {
    return Optional.ofNullable(wishRequest)
        .map(
            request ->
                Wish.builder()
                    .customerId(request.getCustomerId())
                    .productId(request.getProductId())
                    .build())
        .orElse(null);
  }

  static WishResponse toResponse(Wish wish) {
    return Optional.ofNullable(wish)
        .map(
            domain ->
                WishResponse.builder()
                    .id(domain.getId())
                    .customerId(domain.getCustomerId())
                    .productId(domain.getProductId())
                    .createdAt(domain.getCreatedAt())
                    .updatedAt(domain.getUpdatedAt())
                    .build())
        .orElse(null);
  }
}
