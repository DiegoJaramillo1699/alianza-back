package com.alianza.practica.domain.exceptions;

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException(String message){
        super(message);
    }

}
