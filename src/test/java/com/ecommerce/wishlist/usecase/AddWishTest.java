package com.ecommerce.wishlist.usecase;

import com.ecommerce.wishlist.adapter.out.WishRepository;
import com.ecommerce.wishlist.domain.Wish;
import com.ecommerce.wishlist.usecase.exception.WishlistLimitReachedException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddWishTest {

  @Mock private WishRepository wishRepository;

  @InjectMocks private AddWish addWish;

  @Test
  void shouldAddWish() {
    final var wish = wish();
    final var fakeWishReturn = fakeWishReturn();
    when(wishRepository.countByCustomerId(wish.getCustomerId())).thenReturn(0);
    when(wishRepository.save(wish)).thenReturn(fakeWishReturn);

    final var addedWish = addWish.execute(wish);

    assertThat(addedWish).isEqualTo(fakeWishReturn);
  }

  @Test
  void shouldReturnSameWishIfItAlreadyExist() {
    final var wish = wish();
    final var fakeWishReturn = fakeWishReturn();
    when(wishRepository.countByCustomerId(wish.getCustomerId())).thenReturn(0);
    when(wishRepository.findOneByCustomerIdAndProductId(wish.getCustomerId(), wish.getProductId()))
        .thenReturn(Optional.of(fakeWishReturn));

    final var addedWish = addWish.execute(wish);

    assertThat(addedWish).isEqualTo(fakeWishReturn);
    verify(wishRepository, never()).save(any());
  }

  @Test
  void shouldFailToAddWishWhenCustomerReachWishlistLimit() {
    final var wish = wish();
    when(wishRepository.countByCustomerId(wish.getCustomerId())).thenReturn(20);

    assertThatThrownBy(() -> addWish.execute(wish))
        .isInstanceOf(WishlistLimitReachedException.class);
  }

  Wish wish() {
    return Wish.builder().customerId("fake-customer-id").productId("fake-product-id").build();
  }

  Wish fakeWishReturn() {
    return wish().toBuilder()
        .id(UUID.randomUUID().toString())
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
  }
}
