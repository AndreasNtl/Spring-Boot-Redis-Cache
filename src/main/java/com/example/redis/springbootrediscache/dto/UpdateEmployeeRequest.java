package com.example.redis.springbootrediscache.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UpdateEmployeeRequest {

    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String jobTitle;
}
