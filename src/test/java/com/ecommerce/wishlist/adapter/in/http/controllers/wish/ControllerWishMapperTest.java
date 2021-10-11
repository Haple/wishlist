package com.ecommerce.wishlist.adapter.in.http.controllers.wish;

import com.ecommerce.wishlist.adapter.in.http.controllers.wish.request.CreateWishRequest;
import com.ecommerce.wishlist.adapter.in.http.controllers.wish.response.WishResponse;
import com.ecommerce.wishlist.adapter.in.http.controllers.wish.response.WishlistResponse;
import com.ecommerce.wishlist.domain.Wish;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControllerWishMapperTest {

  @Test
  void shouldConvertRequestToDomain() {
    assertThat(ControllerWishMapper.toDomain(request()))
        .usingRecursiveComparison()
        .isEqualTo(domainFromRequest());
  }

  @Test
  void shouldConvertDomainToResponse() {
    assertThat(ControllerWishMapper.toResponse(domain()))
        .usingRecursiveComparison()
        .isEqualTo(response());
  }

  @Test
  void shouldConvertDomainListToWishlistResponse() {
    assertThat(ControllerWishMapper.toWishlistResponse(domains()))
        .usingRecursiveComparison()
        .isEqualTo(wishResponse());
  }

  @Test
  void shouldBeNullWhenThereIsNoRequestToConvert() {
    assertThat(ControllerWishMapper.toDomain(null)).isNull();
  }

  @Test
  void shouldBeNullWhenThereIsNoDomainToConvert() {
    assertThat(ControllerWishMapper.toResponse(null)).isNull();
  }

  @Test
  void shouldBeNullWhenThereIsNoDomainsToConvert() {
    assertThat(ControllerWishMapper.toWishlistResponse(null)).isNull();
  }

  Wish domain() {
    return Wish.builder()
        .id("fake-id")
        .customerId("fake-customer-id")
        .productId("fake-product-id")
        .createdAt(LocalDateTime.parse("2021-10-09T00:00:00"))
        .updatedAt(LocalDateTime.parse("2021-10-09T00:00:00"))
        .build();
  }

  Wish domainFromRequest() {
    return Wish.builder().customerId("fake-customer-id").productId("fake-product-id").build();
  }

  CreateWishRequest request() {
    return CreateWishRequest.builder()
        .customerId("fake-customer-id")
        .productId("fake-product-id")
        .build();
  }

  WishResponse response() {
    return WishResponse.builder()
        .id("fake-id")
        .customerId("fake-customer-id")
        .productId("fake-product-id")
        .createdAt(LocalDateTime.parse("2021-10-09T00:00:00"))
        .updatedAt(LocalDateTime.parse("2021-10-09T00:00:00"))
        .build();
  }

  List<Wish> domains() {
    return Collections.singletonList(domain());
  }

  WishlistResponse wishResponse() {
    return WishlistResponse.builder().wishes(Collections.singletonList(response())).build();
  }
}
