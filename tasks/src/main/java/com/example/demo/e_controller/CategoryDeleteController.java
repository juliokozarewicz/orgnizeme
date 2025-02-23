package com.example.demo.e_controller;

import com.example.demo.c_validation.UUIDValidation;
import com.example.demo.d_service.CategoryDeleteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping()
@Validated
class CategoryDeleteController {

    @Autowired
    private CategoryDeleteService categoryDeleteService;

    @DeleteMapping("${BASE_URL_TASKS:default}/category/delete/{id}")
    public ResponseEntity handle(

        @Valid @PathVariable UUIDValidation id

    ) {

        // Validated data
        Map<String, Object> validatedData = new LinkedHashMap<>();
        validatedData.put("id", id.id());

        return categoryDeleteService.execute(validatedData);

    }

}