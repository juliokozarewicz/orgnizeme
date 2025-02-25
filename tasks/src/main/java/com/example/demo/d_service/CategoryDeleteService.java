package com.example.demo.d_service;

import com.example.demo.a_entity.CategoryEntity;
import com.example.demo.b_repository.CategoryRepository;
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
public class CategoryDeleteService {

    // locale
    @Autowired
    private MessageSource messageSource;

    // database
    @Autowired
    private CategoryRepository categoryRepository;

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
        Optional<CategoryEntity> existingId = categoryRepository
            .findById(id);

        if (existingId.isEmpty()) {
            // call custom error
            errorHandler.customErrorThrow(
                404,
                messageSource.getMessage(
                    "category_not_found", null, locale
                )
            );
        }

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/tasks/category/delete/" + id);
        customLinks.put("next", "/tasks/category/list");

        // delete by id
        categoryRepository.deleteById(id);

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(200)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                    "category_deleted_success", null, locale
                )
            )
            .links(customLinks)
            .build();

        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}