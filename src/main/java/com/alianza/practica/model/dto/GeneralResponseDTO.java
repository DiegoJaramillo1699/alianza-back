package com.alianza.practica.model.dto;

import lombok.Data;

@Data
public class GeneralResponseDTO<T> {
    private Integer statusCode;
    private String message;
    private T response;
}
