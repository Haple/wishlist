package com.ecommerce.wishlist.usecase;

import com.ecommerce.wishlist.adapter.out.WishRepository;
import com.ecommerce.wishlist.domain.Wish;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetWishlistTest {

  @Mock private WishRepository wishRepository;

  @InjectMocks private GetWishlist getWishlist;

  @Test
  void shouldGetWishlist() {
    var wish = wish();
    when(wishRepository.findByCustomerId(wish.getCustomerId()))
        .thenReturn(Collections.singletonList(wish));

    List<Wish> wishes = getWishlist.execute(wish.getCustomerId());

    assertThat(wishes).hasSize(1).hasSameElementsAs(Collections.singletonList(wish));
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
