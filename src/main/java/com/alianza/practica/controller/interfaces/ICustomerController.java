package com.alianza.practica.controller.interfaces;

import com.alianza.practica.domain.exceptions.UserAlreadyExistsException;
import com.alianza.practica.domain.exceptions.UserNotFoundBySharedKeyException;
import com.alianza.practica.model.dto.CustomerDTO;
import com.alianza.practica.model.dto.GeneralResponseDTO;
import com.alianza.practica.model.dto.GetCustomerRequestDTO;
import com.alianza.practica.model.dto.SaveCustomerRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("v1/customer")
public interface ICustomerController {

    @PostMapping(value = "/consultBySharedKey")
    public ResponseEntity<GeneralResponseDTO<CustomerDTO>> consultBySharedKey(@RequestBody final GetCustomerRequestDTO request) throws UserNotFoundBySharedKeyException;

    @PostMapping(value = "/saveCustomer")
    public ResponseEntity<GeneralResponseDTO<String>> saveCustomer(@RequestBody final SaveCustomerRequestDTO request) throws UserAlreadyExistsException;

    @GetMapping(value = "/getAllClients")
    public ResponseEntity<GeneralResponseDTO<List<CustomerDTO>>> getAllClients();
}
