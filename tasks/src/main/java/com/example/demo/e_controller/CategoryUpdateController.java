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

        @PathVariable UUIDValidation id,

        @RequestBody CategoryUpdateValidation categoryUpdateValidation,
        BindingResult bindingResult

    ) {

        // return validation errors
        if (bindingResult.hasErrors()) {

            // field error response
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("errorCode", 400);
            bindingResult.getAllErrors().forEach(error -> {
                String field = (
                    (org.springframework.validation.FieldError) error
                ).getField();
                String messageError = error.getDefaultMessage();
                response.put("field", field);
                response.put("message", messageError);
            });

            throw new RuntimeException(response.toString());
        }

        // Validated data
        Map<String, Object> validatedData = new LinkedHashMap<>();
        validatedData.put("id", id);
        validatedData.put(
            "newCategoryName", categoryUpdateValidation.newCategoryName())
        ;

        return CategoryUpdateService.execute(validatedData);

    }

}