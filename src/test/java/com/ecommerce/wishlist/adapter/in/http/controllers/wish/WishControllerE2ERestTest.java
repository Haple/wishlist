package com.ecommerce.wishlist.adapter.in.http.controllers.wish;

import com.ecommerce.wishlist.adapter.in.http.controllers.wish.request.CreateWishRequest;
import com.ecommerce.wishlist.adapter.out.mongodb.wish.MongodbWishRepository;
import com.ecommerce.wishlist.adapter.out.mongodb.wish.WishSchema;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasEntry;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class WishControllerE2ERestTest {

  @Container static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.port", mongoDBContainer::getFirstMappedPort);
  }

  @LocalServerPort private int port;

  @Autowired private MongodbWishRepository mongodbWishRepository;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @AfterEach
  void tearDown() {
    mongodbWishRepository.deleteAll();
  }

  @Test
  void shouldCreateWish() {
    given()
        .contentType(ContentType.JSON)
        .body(CreateWishRequest.builder().customerId("c1").productId("p1").build())
        .when()
        .post("/v1/wishes")
        .then()
        .statusCode(201)
        .body("id", notNullValue())
        .body("customerId", equalTo("c1"))
        .body("productId", equalTo("p1"))
        .body("createdAt", notNullValue())
        .body("updatedAt", notNullValue());
  }

  @Test
  void shouldFailToCreateWishWhenReachWishlistLimit() {
    createWishNTimes(WishSchema.builder().customerId("c1").productId("p1").build(), 20);

    given()
        .contentType(ContentType.JSON)
        .body(CreateWishRequest.builder().customerId("c1").productId("p1").build())
        .when()
        .post("/v1/wishes")
        .then()
        .statusCode(409)
        .body("message", equalTo("Wishlist reached the limit of 20 wishes."));
  }

  @Test
  void shouldFailToCreateWishWithoutRequiredFields() {
    given()
        .contentType(ContentType.JSON)
        .body(CreateWishRequest.builder().build())
        .when()
        .post("/v1/wishes")
        .then()
        .statusCode(400)
        .body("message", equalTo("Fields validation error."))
        .body(
            "fields",
            hasItem(
                allOf(
                    hasEntry("field", "customerId"),
                    hasEntry("message", "Customer id cannot be blank."))))
        .body(
            "fields",
            hasItem(
                allOf(
                    hasEntry("field", "productId"),
                    hasEntry("message", "Product id cannot be blank."))));
  }

  void createWishNTimes(WishSchema wishSchema, int times) {
    var wishes = new ArrayList<WishSchema>();
    for (int i = 0; i < times; i++) {
      wishes.add(wishSchema);
    }
    mongodbWishRepository.saveAll(wishes);
  }
}
