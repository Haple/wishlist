package com.ecommerce.wishlist.adapter.in.http.controllers.wish;

import com.ecommerce.wishlist.adapter.in.http.controllers.wish.request.CreateWishRequest;
import com.ecommerce.wishlist.adapter.in.http.controllers.wish.response.WishResponse;
import com.ecommerce.wishlist.adapter.in.http.exception.StandardError;
import com.ecommerce.wishlist.usecase.AddWish;
import com.ecommerce.wishlist.usecase.DeleteWish;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ecommerce.wishlist.adapter.in.http.controllers.wish.ControllerWishMapper.toDomain;
import static com.ecommerce.wishlist.adapter.in.http.controllers.wish.ControllerWishMapper.toResponse;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/v1/wishes")
public class WishController {

  private final AddWish addWish;
  private final DeleteWish deleteWish;

  @ApiOperation(
      value = "Add wish to customer wishlist",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "Created wish", response = WishResponse.class),
        @ApiResponse(code = 409, message = "Wishlist limit reached", response = StandardError.class)
      })
  @PostMapping
  public ResponseEntity<WishResponse> create(@RequestBody @Valid CreateWishRequest request) {
    log.info("Create wish request received: {}", request);
    var result = addWish.execute(toDomain(request));
    log.info("Create wish request return: {}", result);
    return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(result));
  }

  @ApiOperation(
      value = "Delete wish from customer wishlist",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {@ApiResponse(code = 204, message = "Wish deleted with success")})
  @DeleteMapping("/{wishId}")
  public ResponseEntity<Void> delete(@PathVariable String wishId) {
    log.info("Delete wish request received: {}", wishId);
    deleteWish.execute(wishId);
    log.info("Delete wish result return");
    return ResponseEntity.noContent().build();
  }
}
