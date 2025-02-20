package com.example.demo.d_service;

import com.example.demo.b_repository.CategoryRepository;
import com.example.demo.utils.interfaces.CategoryProjection;
import com.example.demo.utils.others.StandardResponse;
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
public class CategoryListService {

    // locale
    @Autowired
    private MessageSource messageSource;

    // database
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity execute() {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        List<CategoryProjection> allCategories = categoryRepository
            .findCategoryFiltered();

        // response META
        Map<String, Object> metaInfo = new LinkedHashMap<>();
        metaInfo.put("totalItems", allCategories.size());

        // response LINKS
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/tasks/category/list-all");
        customLinks.put("next", "/tasks/category/update");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(200)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                    "get_data_success", null, locale
                )
            )
            .data(allCategories)
            .meta(metaInfo)
            .links(customLinks)
            .build();

        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}