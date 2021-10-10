package com.ecommerce.wishlist.adapter.in.http.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@ApiModel("Object of a standard error")
public class StandardError {

  @ApiModelProperty("Error message")
  private String message;

  @ApiModelProperty("Fields with error")
  private List<ErrorField> fields;

  @Data
  @Builder
  public static class ErrorField {

    @ApiModelProperty("Field with problem")
    private String field;

    @ApiModelProperty("Message describing the problem")
    private String message;
  }
}
