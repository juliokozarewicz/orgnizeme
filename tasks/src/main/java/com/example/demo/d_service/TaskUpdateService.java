package com.example.demo.d_service;

import com.example.demo.a_entity.TaskEntity;
import com.example.demo.b_repository.TaskRepository;
import com.example.demo.c_validation.TaskUpdateValidation;
import com.example.demo.c_validation.UUIDValidation;
import com.example.demo.f_middlewares.ErrorHandler;
import com.example.demo.utils.others.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class TaskUpdateService {

    // locale
    @Autowired
    private MessageSource messageSource;

    // database
    @Autowired
    private TaskRepository taskRepository;

    // error handler
    @Autowired
    private ErrorHandler errorHandler;

    public ResponseEntity execute(
        UUIDValidation id,
        TaskUpdateValidation validatedBody
    ) {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // verify task
        Optional<TaskEntity> existingTaskId = taskRepository
            .findById(id.id());

        // id not found
        if (existingTaskId.isEmpty()) {
            // call custom error
            errorHandler.customErrorThrow(
                404,
                messageSource.getMessage(
                    "task_not_found", null, locale
                )
            );
        }

        // due date
        LocalDate dueDate = validatedBody.dueDate() != null
            ? validatedBody.dueDate().toInstant()
            .atZone(ZoneId.of("UTC"))
            .toLocalDate()
            : existingTaskId.get().getDueDate();

        // verify task
        Optional<TaskEntity> existingTask = taskRepository
            .findByTaskNameAndDueDate(validatedBody.taskName(), dueDate);

        if (!existingTask.isEmpty()) {
            // call custom error
            errorHandler.customErrorThrow(
                409,
                messageSource.getMessage(
                    "task_created_conflict", null, locale
                )
            );
        }

        // record category
        ZonedDateTime nowUtc = ZonedDateTime.now(ZoneOffset.UTC);
        Timestamp nowTimestamp = Timestamp.from(nowUtc.toInstant());

        // Save the new data
        TaskEntity newTask = TaskEntity.builder()
            .id(id.id())
            .updatedAt(nowTimestamp.toLocalDateTime())
            .taskName(
                (
                    validatedBody.taskName() != null &&
                    !validatedBody.taskName().trim().isEmpty()
                )
                ? validatedBody.taskName().trim()
                : existingTaskId.get().getTaskName()
            )
            .description(
                (
                    validatedBody.description() != null &&
                    !validatedBody.description().trim().isEmpty()
                )
                ? validatedBody.description().trim()
                : existingTaskId.get().getDescription()
            )
            .category(
                (
                    validatedBody.category() != null &&
                    !validatedBody.category().trim().trim().isEmpty()
                )
                ? validatedBody.category().trim()
                : existingTaskId.get().getCategory()
            )
            .priority(
                (
                    validatedBody.priority() != null &&
                    !validatedBody.priority().trim().isEmpty()
                )
                ? validatedBody.priority().trim()
                : existingTaskId.get().getPriority()
            )
            .status(
                (
                    validatedBody.status() !=null &&
                    !validatedBody.status().trim().isEmpty()
                )
                ? validatedBody.status().trim()
                : existingTaskId.get().getStatus()
            )
            .dueDate(dueDate)
            .build();

        taskRepository.save(newTask);

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/tasks/update/" + id.id());
        customLinks.put("next", "/tasks/list");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(200)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                    "task_updated_success", null, locale
                )
            )
            .links(customLinks)
            .build();

        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}