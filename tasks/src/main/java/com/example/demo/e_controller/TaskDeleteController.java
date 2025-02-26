package com.example.demo.e_controller;

import com.example.demo.c_validation.UUIDValidation;

import com.example.demo.d_service.TaskDeleteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
class TaskDeleteController {

    @Autowired
    private TaskDeleteService taskDeleteService;

    @DeleteMapping("${BASE_URL_TASKS:default}/delete/{id}")
    public ResponseEntity handle(

        @Valid @PathVariable UUIDValidation id

    ) {

        // Validated data
        Map<String, Object> validatedData = new LinkedHashMap<>();
        validatedData.put("id", id.id());

        return taskDeleteService.execute(validatedData);

    }

}