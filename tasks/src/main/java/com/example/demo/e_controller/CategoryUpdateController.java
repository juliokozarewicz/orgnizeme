package com.example.demo.e_controller;

import com.example.demo.c_validation.CategoryUpdateValidation;
import com.example.demo.c_validation.UUIDValidation;
import com.example.demo.d_service.CategoryUpdateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping()
@Validated
class CategoryUpdateController {

    @Autowired
    private CategoryUpdateService CategoryUpdateService;

    @PostMapping("${BASE_URL_TASKS:default}/category/update/{id}")
    public ResponseEntity handle(

        @Valid @PathVariable UUIDValidation id,

        @Valid @RequestBody CategoryUpdateValidation categoryUpdateValidation,
        BindingResult bindingResult

    ) {

        // Validated data
        Map<String, Object> validatedData = new LinkedHashMap<>();
        validatedData.put("id", id.id());
        validatedData.put(
            "newCategoryName", categoryUpdateValidation.newCategoryName())
        ;

        return CategoryUpdateService.execute(validatedData);

    }

}