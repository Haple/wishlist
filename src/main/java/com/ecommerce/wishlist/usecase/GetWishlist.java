package com.ecommerce.wishlist.usecase;

import com.ecommerce.wishlist.adapter.out.WishRepository;
import com.ecommerce.wishlist.domain.Wish;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class GetWishlist {

  private final WishRepository wishRepository;

  public List<Wish> execute(final String customerId) {
    return wishRepository.findByCustomerId(customerId);
  }
}
