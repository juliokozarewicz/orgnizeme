package com.example.demo.c_validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UUIDValidation(

    @NotBlank(message = "{not_empty}")
    @Size(min=1, message="{not_empty}")
    @Size(max=50, message="{many_characters}")
    @Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "{invalid_uuid}"
    )
    String UUID

) {}
