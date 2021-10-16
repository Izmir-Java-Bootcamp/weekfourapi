package com.kodluyoruz.weekfourapi.exception.handler;

import com.kodluyoruz.weekfourapi.exception.NotFoundException;
import com.kodluyoruz.weekfourapi.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception) {
        log.error("Exception is occured. Exception:{}", exception.getStackTrace());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .exceptionType(exception.getClass().getSimpleName())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleValidation(MethodArgumentNotValidException exception) {
        List<ErrorResponse> response = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(item -> ErrorResponse.builder()
                        .message(item.getObjectName() + ": " + item.getDefaultMessage())
                        .exceptionType(item.getObjectName())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleBindException(MethodArgumentTypeMismatchException exception) {
        ErrorResponse response = ErrorResponse.builder()
                .message(exception.getMessage())
                .exceptionType(exception.getName())
                .build();
        return ResponseEntity.badRequest().body(response);
    }
}
