package com.greencart.backend.Exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> handleIllegal(IllegalArgumentException ex) {
        Map<String,String> map = new HashMap<>();
        map.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String,String> map = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> map.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleAll(Exception ex) {
        ex.printStackTrace();
        Map<String,String> map = new HashMap<>();
        map.put("error", "Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
    }
}
