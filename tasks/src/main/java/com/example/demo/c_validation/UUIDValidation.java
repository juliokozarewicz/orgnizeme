package com.example.demo.c_validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UUIDValidation(

    @NotBlank(message = "{not_empty}")
    @Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "{invalid_uuid}"
    )
    String UUID

) {}
