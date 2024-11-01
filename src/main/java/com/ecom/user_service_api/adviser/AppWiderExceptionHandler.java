package com.ecom.user_service_api.adviser;

import com.ecom.user_service_api.exception.EntryNotFoundException;
import com.ecom.user_service_api.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWiderExceptionHandler {
    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<StandardResponse> handleEntryNotFoundException(EntryNotFoundException entryNotFoundException){
        return new ResponseEntity<>(
                new StandardResponse(404,entryNotFoundException, entryNotFoundException.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
