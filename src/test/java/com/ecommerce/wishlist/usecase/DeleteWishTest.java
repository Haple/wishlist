package com.ecommerce.wishlist.usecase;

import com.ecommerce.wishlist.adapter.out.WishRepository;
import com.ecommerce.wishlist.domain.Wish;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteWishTest {

  @Mock private WishRepository wishRepository;

  @InjectMocks private DeleteWish deleteWish;

  @Test
  void shouldDeleteWish() {
    final var wish = wish();
    doNothing().when(wishRepository).delete(wish.getId());

    deleteWish.execute(wish.getId());

    verify(wishRepository).delete(wish.getId());
  }

  Wish wish() {
    return Wish.builder().customerId("fake-customer-id").productId("fake-product-id").build();
  }
}
