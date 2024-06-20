package com.alianza.practica.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class CustomerDTO {

    private String sharedKey;
    private String businessId;
    private String email;
    private String phone;
    private LocalDate createdAt;
}
