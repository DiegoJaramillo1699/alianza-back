package com.alianza.practica.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Data
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "shared_key", unique = true)
    private String sharedKey;
    @Column(name = "business_id")
    private String businessId;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    public CustomerEntity() {

    }
}
