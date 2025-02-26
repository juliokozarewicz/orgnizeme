package com.example.demo.d_service;

import com.example.demo.a_entity.TaskEntity;
import com.example.demo.b_repository.TaskRepository;
import com.example.demo.utils.middlewares.ErrorHandler;
import com.example.demo.utils.others.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskDeleteService {

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
        Map<String, Object> validatedData
    ) {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // Map vars
        String id = (String) validatedData.get("id");

        // verify id
        Optional<TaskEntity> existingId = taskRepository
            .findById(id);

        if (existingId.isEmpty()) {
            // call custom error
            errorHandler.customErrorThrow(
                404,
                messageSource.getMessage(
                    "task_not_found", null, locale
                )
            );
        }

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/tasks/delete/" + id);
        customLinks.put("next", "/tasks/list");

        // delete by id
        taskRepository.deleteById(id);

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(200)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                    "task_deleted_success", null, locale
                )
            )
            .links(customLinks)
            .build();

        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}