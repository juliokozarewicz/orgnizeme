package com.example.demo.d_service;

import com.example.demo.f_utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class HelloWorldService {

    // locale
    @Autowired
    private MessageSource messageSource;

    public ResponseEntity execute(
        String message
    ) {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/helloworld/helloworld");
        customLinks.put("next", "/documentation/swagger");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(200)
            .statusMessage("success")
            .message(message)
            .links(customLinks)
            .build();

        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}