package com.ecommerce.wishlist.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class Wish {
  private String id;
  private String customerId;
  private String productId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
