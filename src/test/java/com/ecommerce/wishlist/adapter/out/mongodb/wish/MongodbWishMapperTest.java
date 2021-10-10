package com.ecommerce.wishlist.adapter.out.mongodb.wish;

import com.ecommerce.wishlist.domain.Wish;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MongodbWishMapperTest {

  @Test
  void shouldConvertSchemaToDomain() {
    assertThat(MongodbWishMapper.toDomain(schema())).usingRecursiveComparison().isEqualTo(domain());
  }

  @Test
  void shouldConvertDomainToSchema() {
    assertThat(MongodbWishMapper.toSchema(domain())).usingRecursiveComparison().isEqualTo(schema());
  }

  @Test
  void shouldBeNullWhenThereIsNoSchemaToConvert() {
    assertThat(MongodbWishMapper.toDomain(null)).isNull();
  }

  @Test
  void shouldBeNullWhenThereIsNoDomainToConvert() {
    assertThat(MongodbWishMapper.toSchema(null)).isNull();
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

  WishSchema schema() {
    return WishSchema.builder()
        .id("fake-id")
        .customerId("fake-customer-id")
        .productId("fake-product-id")
        .createdAt(LocalDateTime.parse("2021-10-09T00:00:00"))
        .updatedAt(LocalDateTime.parse("2021-10-09T00:00:00"))
        .build();
  }
}
