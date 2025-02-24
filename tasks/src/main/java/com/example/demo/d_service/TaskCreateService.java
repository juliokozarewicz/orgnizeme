package com.example.demo.d_service;

import com.example.demo.a_entity.TaskEntity;
import com.example.demo.b_repository.TaskRepository;
import com.example.demo.c_validation.TaskCreateValidation;
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

    public ResponseEntity execute(
        TaskCreateValidation validatedBody
    ) {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // verify task
        // By task name #####
        // By due date #####
        List<TaskEntity> existingTask = taskRepository
            .findByTaskName(validatedBody.taskName());

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
        String generatedUUID = UUID.randomUUID().toString();
        ZonedDateTime nowUtc = ZonedDateTime.now(ZoneOffset.UTC);
        Timestamp nowTimestamp = Timestamp.from(nowUtc.toInstant());

        // due date
        LocalDate dueDate = validatedBody.dueDate()
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();

        // Save the new task to the DB
        TaskEntity newTask = new TaskEntity(
            generatedUUID,
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