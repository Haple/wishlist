package com.ecommerce.wishlist.adapter.in.http.controllers.wish.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@EqualsAndHashCode
@ApiModel("Object of a saved wish")
public class WishResponse {

  @ApiModelProperty("Unique identifier of the wish")
  private String id;

  @ApiModelProperty("Unique identifier of the customer")
  private String customerId;

  @ApiModelProperty("Unique identifier of the product")
  private String productId;

  @ApiModelProperty("Date of the wish creation")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime createdAt;

  @ApiModelProperty("Date of the wish change")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime updatedAt;
}
