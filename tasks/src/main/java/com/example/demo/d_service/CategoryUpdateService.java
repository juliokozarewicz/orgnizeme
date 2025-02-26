package com.example.demo.d_service;

import com.example.demo.a_entity.CategoryEntity;
import com.example.demo.b_repository.CategoryRepository;
import com.example.demo.f_middlewares.ErrorHandler;
import com.example.demo.utils.others.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class CategoryUpdateService {

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
        String updateCategoryName = (String) validatedData.get("updateCategoryName");

        // verify id
        Optional<CategoryEntity> existingId = categoryRepository
            .findById(id);

        // id not found
        if (existingId.isEmpty()) {
            // call custom error
            errorHandler.customErrorThrow(
                404,
                messageSource.getMessage(
                    "category_not_found", null, locale
                )
            );
        }

        // verify category
        Optional<CategoryEntity> existingCategory = categoryRepository
            .findByCategoryName(updateCategoryName);

        if (!existingCategory.isEmpty()) {
            // call custom error
            errorHandler.customErrorThrow(
                409,
                messageSource.getMessage(
                    "category_created_conflict", null, locale
                )
            );
        }

        // record category
        ZonedDateTime nowUtc = ZonedDateTime.now(ZoneOffset.UTC);
        Timestamp nowTimestamp = Timestamp.from(nowUtc.toInstant());

        // Create a new CategoryEntity using builder
        CategoryEntity newCategory = CategoryEntity.builder()
            .id(id)
            .updatedAt(nowTimestamp.toLocalDateTime())
            .categoryName(updateCategoryName.trim())
            .build();

        categoryRepository.save(newCategory);

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/tasks/category/update/" + id.toString());
        customLinks.put("next", "/tasks/category/list");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(200)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                    "category_updated_success", null, locale
                )
            )
            .links(customLinks)
            .build();

        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}