package com.ecommerce.wishlist.adapter.in.http.controllers.wish.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Object used to create a wish")
public class CreateWishRequest {

  @ApiModelProperty("Unique identifier of the customer")
  @NotBlank
  private String customerId;

  @ApiModelProperty("Unique identifier of the product")
  @NotBlank
  private String productId;
}
