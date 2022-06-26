package com.example.redis.springbootrediscache.validations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.Instant;

@Getter
@Setter
public class ErrorResponse {

    private String status;
    private String message;
    private Instant timestamp;
    private String path;
    private int code;

    public ErrorResponse(String message, Instant timestamp, String path, HttpStatus httpStatus) {
        this.status = httpStatus.name();
        this.timestamp = timestamp;
        this.path = path;
        this.message = message;
        this.code = httpStatus.value();
    }
}
