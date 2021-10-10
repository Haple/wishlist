package com.ecommerce.wishlist.adapter.out.mongodb.wish;

import com.ecommerce.wishlist.domain.Wish;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class WishRepositoryImplIntegrationTest {

  @Container static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.port", mongoDBContainer::getFirstMappedPort);
  }

  @Autowired private MongodbWishRepository mongodbWishRepository;

  private WishRepositoryImpl wishRepository;

  @BeforeEach
  void setUp() {
    wishRepository = new WishRepositoryImpl(mongodbWishRepository);
  }

  @AfterEach
  void tearDown() {
    mongodbWishRepository.deleteAll();
  }

  @Test
  void shouldSave() {
    var result = wishRepository.save(wish());

    assertThat(result.getId()).isNotBlank();
    assertThat(result.getCreatedAt()).isNotNull();
    assertThat(result.getUpdatedAt()).isNotNull();
  }

  @Test
  void countByCustomerId() {
    Wish wish = wish();
    wishRepository.save(wish.toBuilder().customerId("other-customer-id").build());
    wishRepository.save(wish);
    wishRepository.save(wish);

    var result = wishRepository.countByCustomerId(wish.getCustomerId());

    assertThat(result).isEqualTo(2);
  }

  Wish wish() {
    return Wish.builder().customerId("fake-customer-id").productId("fake-product-id").build();
  }
}
