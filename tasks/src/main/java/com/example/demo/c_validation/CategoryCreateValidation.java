package com.example.demo.c_validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryCreateValidation(

        @NotBlank(message = "{not_empty}")
        @Size(min=1, message="{not_empty}")
        @Size(max=100, message="{many_characters}")
        String categoryName

) {}