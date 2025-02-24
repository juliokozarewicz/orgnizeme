package com.example.demo.e_controller;


import com.example.demo.c_validation.TaskUpdateValidation;
import com.example.demo.c_validation.UUIDValidation;
import com.example.demo.d_service.TaskUpdateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@Validated
class TaskUpdateController {

    @Autowired
    private TaskUpdateService taskUpdateService;

    @PutMapping("${BASE_URL_TASKS:default}/update/{id}")
    public ResponseEntity handle(

        @Valid @PathVariable UUIDValidation id,

        @Valid @RequestBody TaskUpdateValidation taskUpdateValidation,
        BindingResult bindingResult

    ) {

        return taskUpdateService.execute(id, taskUpdateValidation);

    }

}