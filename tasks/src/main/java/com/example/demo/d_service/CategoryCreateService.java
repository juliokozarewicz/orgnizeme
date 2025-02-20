package com.example.demo.d_service;

import com.example.demo.a_entity.CategoryEntity;
import com.example.demo.b_repository.CategoryRepository;
import com.example.demo.c_validation.CategoryCreateValidation;
import com.example.demo.f_utils.others.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class CategoryCreateService {

    // locale
    @Autowired
    private MessageSource messageSource;

    // database
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity execute(
        CategoryCreateValidation validatedBody
    ) {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // verify category
        List<CategoryEntity> existingCategory = categoryRepository.findByCategoryName(validatedBody.categoryName());

        if (!existingCategory.isEmpty()) {
            // call custom error
            Map<String, Object> errorDetails = new LinkedHashMap<>();
            errorDetails.put("errorCode", 409);
            errorDetails.put(
                "message",
                messageSource.getMessage(
                    "category_created_conflict", null, locale
                )
            );
            throw new RuntimeException(errorDetails.toString());
        }

        // record category
        CategoryEntity categoryEntity = CategoryEntity
            .createCategory(validatedBody.categoryName());
        categoryRepository.save(categoryEntity);

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
            .links(customLinks)
            .build();

        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}