package com.ecommerce.wishlist.adapter.in.http.controllers.wish;

import com.ecommerce.wishlist.adapter.in.http.controllers.wish.request.CreateWishRequest;
import com.ecommerce.wishlist.adapter.in.http.controllers.wish.response.WishResponse;
import com.ecommerce.wishlist.adapter.in.http.controllers.wish.response.WishlistResponse;
import com.ecommerce.wishlist.domain.Wish;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  static WishlistResponse toWishlistResponse(List<Wish> wishes) {
    return Optional.ofNullable(wishes)
        .map(
            domains ->
                WishlistResponse.builder()
                    .wishes(
                        domains.stream()
                            .map(ControllerWishMapper::toResponse)
                            .collect(Collectors.toList()))
                    .build())
        .orElse(null);
  }
}
