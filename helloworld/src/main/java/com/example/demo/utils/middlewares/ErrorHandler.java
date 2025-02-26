package com.example.demo.utils.middlewares;

import com.example.demo.utils.dtos.StandardResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

// locale
import org.springframework.context.MessageSource;
import java.util.Locale;

// logs
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(
        ErrorHandler.class
    );

    // locale
    @Autowired
    private MessageSource messageSource;

    // error throw
    public void customErrorThrow (
        int errorCode,
        String message
    ) {
        // locale
        Locale locale = LocaleContextHolder.getLocale();

        // call error
        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put("errorCode", errorCode);
        errorDetails.put("message", message);
        throw new RuntimeException(errorDetails.toString());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleAllExceptions(
        Exception error
    ) {

        try {

            // locale
            Locale locale = LocaleContextHolder.getLocale();

            // validation error
            if (error instanceof ConstraintViolationException) {

                var violation = ((ConstraintViolationException) error)
                    .getConstraintViolations().iterator().next();

                // field name
                String fieldName = violation.getPropertyPath().toString();
                String[] fieldParts = fieldName.split("\\.");
                String lastFieldName = fieldParts[fieldParts.length - 1];

                // error validation message
                String errorValidationMessage = violation.getMessage();

                StandardResponse response = new StandardResponse.Builder()
                    .statusCode(400)
                    .statusMessage("error")
                    .field(lastFieldName)
                    .message(errorValidationMessage)
                    .build();

                return ResponseEntity
                    .status(response.getStatusCode())
                    .body(response);
            }

            // bad request
            if (
                error instanceof HttpMessageNotReadableException ||
                    error instanceof NoResourceFoundException
            ) {

                StandardResponse response = new StandardResponse.Builder()
                    .statusCode(400)
                    .statusMessage("error")
                    .message(
                        messageSource.getMessage(
                            "bad_request", null, locale
                        )
                    )
                    .build();

                return ResponseEntity
                    .status(response.getStatusCode())
                    .body(response);
            }

            // get error itens
            String errorMessage = error.getMessage();
            if (errorMessage == null || errorMessage.isEmpty()) {
                errorMessage = "Unknown error occurred";
            } else {
                errorMessage = errorMessage.substring(1, errorMessage.length() - 1);
            }

            Map<String, Object> errorMap = new LinkedHashMap<>();
            String[] keyValuePairs = errorMessage.split(", ");

            for (String line : keyValuePairs) {
                String[] KeyIten = line.split("=");
                errorMap.put(KeyIten[0], KeyIten[1]);
            }

            String errorCode = (String) errorMap.get("errorCode");
            String errorMessageDetail = (String) errorMap.get("message");

            StandardResponse response = new StandardResponse.Builder()
                .statusCode(Integer.parseInt(errorCode))
                .statusMessage("error")
                .message(errorMessageDetail)
                .build();

            return ResponseEntity
                .status(response.getStatusCode())
                .body(response);

        } catch (Exception e) {

            // locale
            Locale locale = LocaleContextHolder.getLocale();

            // logs
            logger.error(error.toString());

            StandardResponse response = new StandardResponse.Builder()
                .statusCode(500)
                .statusMessage("error")
                .message(
                    messageSource.getMessage(
                        "server_error", null, locale
                    )
                )
                .build();

            return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
        }

    }

}