package com.example.demo.d_service;

import com.example.demo.a_entity.CategoryEntity;
import com.example.demo.b_repository.CategoryRepository;
import com.example.demo.c_validation.CategoryCreateValidation;
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
public class CategoryCreateService {

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
        CategoryCreateValidation validatedBody
    ) {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // verify category
        Optional<CategoryEntity> existingCategory = categoryRepository
            .findByCategoryName(validatedBody.categoryName().trim());

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
        String generatedUUID = UUID.randomUUID().toString();
        ZonedDateTime nowUtc = ZonedDateTime.now(ZoneOffset.UTC);
        Timestamp nowTimestamp = Timestamp.from(nowUtc.toInstant());

        // Create a new CategoryEntity using builder
        CategoryEntity newCategory = CategoryEntity.builder()
            .id(generatedUUID)
            .createdAt(nowTimestamp.toLocalDateTime())
            .updatedAt(nowTimestamp.toLocalDateTime())
            .categoryName(validatedBody.categoryName().trim())
            .build();

        categoryRepository.save(newCategory);

        // response META
        Map<String, Object> metaInfo = new LinkedHashMap<>();
        metaInfo.put("idCreated", generatedUUID);

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/tasks/category/create");
        customLinks.put("next", "/tasks/category/list");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(201)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                    "category_created_success", null, locale
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