package com.ecommerce.wishlist.adapter.in.http.exception;

import com.ecommerce.wishlist.usecase.exception.BusinessRuleException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class ControllerExceptionHandler {

  private final MessageSource messageSource;

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<StandardError> handleException(final Exception ex) {
    log.error("Exception:", ex);
    var error = StandardError.builder().message(ex.getMessage()).build();
    log.error("Returning internal server error with standard error {}", error);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }

  @ExceptionHandler(BusinessRuleException.class)
  protected ResponseEntity<StandardError> handleBusinessRuleException(
      final BusinessRuleException ex, Locale locale) {
    log.error("BusinessRuleException:", ex);
    var error =
        StandardError.builder()
            .message(messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale))
            .build();
    log.error("Returning conflict with standard error {}", error);
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardError> handleArgumentNotValidException(
      final MethodArgumentNotValidException ex, final Locale locale) {
    log.error("MethodArgumentNotValidException:", ex);
    List<StandardError.ErrorField> errorFields = new ArrayList<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errorFields.add(
          StandardError.ErrorField.builder()
              .field(error.getField())
              .message(messageSource.getMessage(error, locale))
              .build());
    }
    var messageCode = "Exception.MethodArgumentNotValidException";
    var error =
        StandardError.builder()
            .message(messageSource.getMessage(messageCode, null, locale))
            .fields(errorFields)
            .build();
    log.error("Returning bad request with standard error {}", error);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<StandardError> handleMissingServletRequestParameterException(
      final MissingServletRequestParameterException ex, final Locale locale) {
    log.error("MissingServletRequestParameterException:", ex);
    var messageCode = "Exception.MissingServletRequestParameterException";
    String[] args = {ex.getParameterName()};
    var error =
        StandardError.builder()
            .message(messageSource.getMessage(messageCode, args, locale))
            .build();
    log.error("Returning bad request with standard error {}", error);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }
}
