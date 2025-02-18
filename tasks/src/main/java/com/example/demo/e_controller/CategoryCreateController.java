package com.example.demo.e_controller;

import com.example.demo.c_validation.CategoryCreateValidation;
import com.example.demo.d_service.CategoryCreateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping()
class CategoryCreateController {

    @Autowired
    private CategoryCreateService categoryCreateService;

    @PostMapping("${BASE_URL_TASKS:default}/category/create")
    public ResponseEntity handle(

        @Valid @RequestBody CategoryCreateValidation categoryCreateValidation,
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

        return categoryCreateService.execute(categoryCreateValidation);

    }

}