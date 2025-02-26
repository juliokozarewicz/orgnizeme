package com.example.demo.d_service;

import com.example.demo.a_entity.TaskEntity;
import com.example.demo.b_repository.TaskRepository;
import com.example.demo.b_repository.especifications.TaskSpecifications;
import com.example.demo.utils.others.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class TaskListService {

    // locale
    @Autowired
    private MessageSource messageSource;

    // database
    @Autowired
    private TaskRepository taskRepository;

    public ResponseEntity execute() {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // query db
        Specification<TaskEntity> spec = TaskSpecifications.filterTasks(
            null,
            null,
            null,
            null,
            null,
            null,
            null
        );
        List<TaskEntity> allTasks = taskRepository.findAll(spec);

        // response META
        Map<String, Object> metaInfo = new LinkedHashMap<>();
        metaInfo.put("totalItems", allTasks.size());

        // response LINKS
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/tasks/list");
        customLinks.put("next", "/tasks/update");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(200)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                    "get_data_success", null, locale
                )
            )
            .data(allTasks)
            .meta(metaInfo)
            .links(customLinks)
            .build();

        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}