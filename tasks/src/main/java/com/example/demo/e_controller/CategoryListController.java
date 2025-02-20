package com.example.demo.e_controller;


import com.example.demo.d_service.CategoryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
class CategoryListController {

    @Autowired
    private CategoryListService categoryListService;

    @GetMapping("${BASE_URL_TASKS:default}/category/list-all")
    public ResponseEntity handle() {

        return categoryListService.execute();

    }

}