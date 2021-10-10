package com.ecommerce.wishlist.adapter.in.http.documentation;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private static final String BASE_PACKAGE = "com.ecommerce.wishlist.adapter.in.http.controllers";

  @Bean
  public Docket docketBean() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
        .build()
        .pathMapping("/")
        .directModelSubstitute(LocalDateDeserializer.class, String.class)
        .directModelSubstitute(LocalDateTimeDeserializer.class, String.class)
        .genericModelSubstitutes(ResponseEntity.class)
        .apiInfo(metaData());
  }

  private ApiInfo metaData() {
    return new ApiInfoBuilder()
        .title("Wishlist")
        .description("E-commerce wishlist")
        .version("1.0.0")
        .build();
  }
}
