package com.example.demo.d_service;

import com.example.demo.a_entity.TaskEntity;
import com.example.demo.b_repository.TaskRepository;
import com.example.demo.c_validation.TaskUpdateValidation;
import com.example.demo.c_validation.UUIDValidation;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class TaskUpdateService {

    // locale
    @Autowired
    private MessageSource messageSource;

    // database
    @Autowired
    private TaskRepository taskRepository;

    public ResponseEntity execute(
        UUIDValidation id,
        TaskUpdateValidation validatedBody
    ) {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // due date
        LocalDate dueDate = validatedBody.dueDate()
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();

        // verify task
        List<TaskEntity> existingTaskId = taskRepository
            .findById(id.id());

        // id not found
        if (existingTaskId.isEmpty()) {
            // call custom error
            Map<String, Object> errorDetails = new LinkedHashMap<>();
            errorDetails.put("errorCode", 404);
            errorDetails.put(
                "message",
                messageSource.getMessage(
                    "task_not_found", null, locale
                )
            );
            throw new RuntimeException(errorDetails.toString());
        }

        // verify task
        List<TaskEntity> existingTask = taskRepository
            .findByTaskNameAndDueDate(validatedBody.taskName(), dueDate);

        if (!existingTask.isEmpty()) {
            // call custom error
            Map<String, Object> errorDetails = new LinkedHashMap<>();
            errorDetails.put("errorCode", 409);
            errorDetails.put(
                "message",
                messageSource.getMessage(
                    "task_created_conflict", null, locale
                )
            );
            throw new RuntimeException(errorDetails.toString());
        }

        // record category
        ZonedDateTime nowUtc = ZonedDateTime.now(ZoneOffset.UTC);
        Timestamp nowTimestamp = Timestamp.from(nowUtc.toInstant());

        // Save the new task to the DB
        TaskEntity newTask = new TaskEntity(
            id.id(),
            nowTimestamp.toLocalDateTime(),
            nowTimestamp.toLocalDateTime(),
            validatedBody.taskName().trim(),
            validatedBody.description().trim(),
            validatedBody.category().trim(),
            validatedBody.priority().trim(),
            validatedBody.status().trim(),
            dueDate
        );
        taskRepository.save(newTask);

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/tasks/update");
        customLinks.put("next", "/tasks/list");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(201)
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