package com.ecommerce.wishlist.adapter.in.http.controllers.wish.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
@EqualsAndHashCode
@ApiModel("Object of a wishlist")
public class WishlistResponse {

  @ApiModelProperty("List with wishes")
  private List<WishResponse> wishes;
}
