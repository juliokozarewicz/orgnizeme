package com.example.demo.e_controller;

import com.example.demo.c_validation.TaskListValidation;
import com.example.demo.d_service.TaskListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@Validated
class TaskListController {

    @Autowired
    private TaskListService taskListService;

    @GetMapping("${BASE_URL_TASKS:default}/list")
    public ResponseEntity handle(

        @Valid TaskListValidation taskListValidation,
        BindingResult bindingResult

    ) {

        return taskListService.execute(taskListValidation);

    }

}