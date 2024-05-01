package com.gontijo.desafiovotacao.error;

import com.gontijo.desafiovotacao.dto.ApiErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {

        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);

        log.error(errors.toString(), ex);

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoudException.class)
    public ResponseEntity<ApiErrorDto> handleResourceNotFoudException(ResourceNotFoudException ex) {

        log.error(ex.getMessage(), ex);

        var apiError = ApiErrorDto.builder()
                .message(ex.getMessage())
                .build();

        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ApiErrorDto> handleUnprocessableEntityException(UnprocessableEntityException ex) {

        log.error(ex.getMessage(), ex);

        var apiError = ApiErrorDto.builder()
                .message(ex.getMessage())
                .build();

        return ResponseEntity.unprocessableEntity().body(apiError);
    }
}
