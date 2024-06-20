package com.alianza.practica.configuration;

import com.alianza.practica.domain.exceptions.UserAlreadyExistsException;
import com.alianza.practica.domain.exceptions.UserNotFoundBySharedKeyException;
import com.alianza.practica.model.dto.GeneralResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({UserNotFoundBySharedKeyException.class,
            UserAlreadyExistsException.class
    })
    public ResponseEntity<GeneralResponseDTO<Object>> handleCustomerNotFoundBySharedKey(Exception ex){
        GeneralResponseDTO<Object> generalResponseDTO = new GeneralResponseDTO<>();
        generalResponseDTO.setStatusCode(-1);
        generalResponseDTO.setMessage(ex.getMessage());

        return ResponseEntity.ok().body(generalResponseDTO);
    }

}
