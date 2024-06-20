package com.alianza.practica.model.dto;

import lombok.Data;
@Data
public class SaveCustomerRequestDTO {
    private String sharedKey;
    private String businessId;
    private String email;
    private String phone;
}
