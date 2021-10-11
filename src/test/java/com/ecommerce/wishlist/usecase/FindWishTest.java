package com.ecommerce.wishlist.usecase;

import com.ecommerce.wishlist.adapter.out.WishRepository;
import com.ecommerce.wishlist.domain.Wish;
import com.ecommerce.wishlist.usecase.exception.ProductNotOnWishlistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindWishTest {

  @Mock private WishRepository wishRepository;

  @InjectMocks private FindWish findWish;

  @Test
  void shouldFindWish() {
    var wish = wish();
    when(wishRepository.findOneByCustomerIdAndProductId(wish.getCustomerId(), wish.getProductId()))
        .thenReturn(Optional.of(wish));

    var result = findWish.execute(wish.getCustomerId(), wish.getProductId());

    assertThat(result).isEqualTo(wish);
  }

  @Test
  void shouldFailToFindWishWhenProductNotOnWishlist() {
    when(wishRepository.findOneByCustomerIdAndProductId(anyString(), anyString()))
        .thenReturn(Optional.empty());

    assertThatThrownBy(() -> findWish.execute("fake-customer-id", "fake-product-id"))
        .isInstanceOf(ProductNotOnWishlistException.class);
  }

  Wish wish() {
    return Wish.builder()
        .id(UUID.randomUUID().toString())
        .customerId("fake-customer-id")
        .productId("fake-product-id")
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
  }
}
