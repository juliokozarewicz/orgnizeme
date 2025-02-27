package com.example.demo.c_validation;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Date;

public record TaskListValidation(

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

    LocalDate initDate,

    LocalDate endDate

) {}