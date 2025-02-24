package com.example.demo.e_controller;

import com.example.demo.c_validation.CategoryCreateValidation;
import com.example.demo.d_service.CategoryCreateService;
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
class CategoryCreateController {

    @Autowired
    private CategoryCreateService categoryCreateService;

    @PostMapping("${BASE_URL_TASKS:default}/category/create")
    public ResponseEntity handle(

        @Valid @RequestBody CategoryCreateValidation categoryCreateValidation,
        BindingResult bindingResult

    ) {

        return categoryCreateService.execute(categoryCreateValidation);

    }

}