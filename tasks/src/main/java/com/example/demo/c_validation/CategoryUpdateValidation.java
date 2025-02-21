package com.example.demo.c_validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CategoryUpdateValidation(

    @NotBlank(message = "{not_empty}")
    @Size(min=1, message="{not_empty}")
    @Size(max=100, message="{many_characters}")
    String newCategoryName

) {}
