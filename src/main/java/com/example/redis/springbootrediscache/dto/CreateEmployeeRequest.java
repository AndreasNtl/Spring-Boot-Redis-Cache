package com.example.redis.springbootrediscache.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Singular;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CreateEmployeeRequest {

    @NotNull(message = "name is mandatory")
    @Size(min = 2, max = 30, message = "name must be between 2 and 30 characters")
    private String name;
    @NotNull(message = "surname is mandatory")
    @Size(min = 2, max = 30, message = "surname must be between 2 and 30 characters")
    private String surname;
    @NotNull(message = "dateOfBirth is mandatory")
    private LocalDate dateOfBirth;
    @Size(min = 2, max = 50, message = "jobTitle must be between 2 and 30 characters")
    @NotNull(message = "jobTitle is mandatory")
    private String jobTitle;
}
