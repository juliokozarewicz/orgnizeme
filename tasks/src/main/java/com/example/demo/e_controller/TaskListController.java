package com.example.demo.e_controller;

import com.example.demo.b_repository.TaskRepository;
import com.example.demo.d_service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
class TaskListController {

    @Autowired
    private TaskListService taskListService;

    @GetMapping("${BASE_URL_TASKS:default}/list")
    public ResponseEntity handle() {

        return taskListService.execute();

    }

}