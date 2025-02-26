package com.example.demo.d_service;

import com.example.demo.a_entity.TaskEntity;
import com.example.demo.b_repository.TaskRepository;
import com.example.demo.c_validation.TaskCreateValidation;
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
public class TaskCreateService {

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
        TaskCreateValidation validatedBody
    ) {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // due date
        LocalDate dueDate = validatedBody.dueDate()
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();

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
        String generatedUUID = UUID.randomUUID().toString();
        ZonedDateTime nowUtc = ZonedDateTime.now(ZoneOffset.UTC);
        Timestamp nowTimestamp = Timestamp.from(nowUtc.toInstant());

        // Save the new task to the DB
        TaskEntity newTask = TaskEntity.builder()
            .id(generatedUUID)
            .createdAt(nowTimestamp.toLocalDateTime())
            .updatedAt(nowTimestamp.toLocalDateTime())
            .taskName(validatedBody.taskName().trim())
            .description(validatedBody.description().trim())
            .category(validatedBody.category().trim())
            .priority(validatedBody.priority().trim())
            .status(validatedBody.status().trim())
            .dueDate(dueDate)
            .build();

        taskRepository.save(newTask);

        // response META
        Map<String, Object> metaInfo = new LinkedHashMap<>();
        metaInfo.put("idCreated", generatedUUID);

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/tasks/create");
        customLinks.put("next", "/tasks/list");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(201)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                    "task_created_success", null, locale
                )
            )
            .meta(metaInfo)
            .links(customLinks)
            .build();

        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}