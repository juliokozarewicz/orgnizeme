package com.example.demo.e_controller;

import com.example.demo.c_validation.TaskCreateValidation;
import com.example.demo.d_service.TaskCreateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping()
@Validated
class TaskCreateController {

    @Autowired
    private TaskCreateService taskCreateService;

    @PostMapping("${BASE_URL_TASKS:default}/create")
    public ResponseEntity handle(

        @Valid @RequestBody TaskCreateValidation taskCreateValidation,
        BindingResult bindingResult

    ) {

        return taskCreateService.execute(taskCreateValidation);

    }

}