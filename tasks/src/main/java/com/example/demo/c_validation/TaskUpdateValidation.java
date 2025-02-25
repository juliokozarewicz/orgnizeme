package com.example.demo.c_validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record TaskUpdateValidation(

    @Size(min = 1, message = "{not_empty}")
    @Size(max = 255, message = "{many_characters}")
    String taskName,

    @Size(min = 1, message = "{not_empty}")
    @Size(max = 1000, message = "{many_characters}")
    String description,

    @Size(min = 1, message = "{not_empty}")
    @Size(max = 255, message = "{many_characters}")
    String category,

    @Size(min = 1, message = "{not_empty}")
    @Size(max = 255, message = "{many_characters}")
    String priority,

    @Size(min = 1, message = "{not_empty}")
    @Size(max = 255, message = "{many_characters}")
    String status,

    Date dueDate

) {}