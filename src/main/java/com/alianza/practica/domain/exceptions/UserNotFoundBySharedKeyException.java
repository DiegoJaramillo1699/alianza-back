package com.alianza.practica.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

public class UserNotFoundBySharedKeyException extends Exception{

    public UserNotFoundBySharedKeyException(String message){
        super(message);
    }

}
