package com.example.demo.e_controller;

import com.example.demo.c_validation.HelloWorldValidation;
import com.example.demo.d_service.HelloWorldService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping()
@Validated
class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping("${BASE_URL_HELLOWORLD:default}/helloworld")
    public ResponseEntity handle(

        // validation errors
        @Valid HelloWorldValidation helloWorldValidation,
        BindingResult bindingResult

    ) {

        // message
        String message = helloWorldValidation.message() != null ?
            helloWorldValidation.message() : "Hello World!";

        return helloWorldService.execute(message);

    }

}