package com.wefood.back.global.exception;

import com.wefood.back.farm.exception.FarmNotFoundException;
import com.wefood.back.global.Message;
import com.wefood.back.product.exception.CategoryNotFoundException;
import com.wefood.back.product.exception.ProductNotFoundException;
import com.wefood.back.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * class: GlobalExceptionHandler.
 *
 * @author JBum
 * @version 2024/08/12
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Message<String>> handleInvalidRequest(InvalidRequestException e) {
        Message<String> message = new Message<>(400, e.getMessage());
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<Message<String>> handleFileUploadException(FileUploadException e) {
        Message<String> message = new Message<>(500, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Message<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        Message<Map<String, String>> message = new Message<>(400, "Validation failed", errors);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler({ProductNotFoundException.class, CategoryNotFoundException.class, UserNotFoundException.class, FarmNotFoundException.class})
    public ResponseEntity<Message<String>> handleNotFoundException(RuntimeException e) {
        Message<String> message = new Message<>(404, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Message<String>> handleGenericException(Exception e) {
        Message<String> message = new Message<>(500, "An unexpected error occurred: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}