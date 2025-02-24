package com.example.demo.c_validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record TaskCreateValidation(

    @NotBlank(message = "{not_empty}")
    @Size(min = 1, message = "{not_empty}")
    @Size(max = 255, message = "{many_characters}")
    String taskName,

    @NotBlank(message = "{not_empty}")
    @Size(min = 1, message = "{not_empty}")
    @Size(max = 1000, message = "{many_characters}")
    String description,

    @NotBlank(message = "{not_empty}")
    @Size(min = 1, message = "{not_empty}")
    @Size(max = 255, message = "{many_characters}")
    String category,

    @NotBlank(message = "{not_empty}")
    @Size(min = 1, message = "{not_empty}")
    @Size(max = 255, message = "{many_characters}")
    String priority,

    @NotBlank(message = "{not_empty}")
    @Size(min = 1, message = "{not_empty}")
    @Size(max = 255, message = "{many_characters}")
    String status,

    @NotNull(message = "{not_empty}")
    Date dueDate

) {}