package com.alianza.practica.controller.implementations;

import com.alianza.practica.controller.interfaces.ICustomerController;
import com.alianza.practica.domain.exceptions.UserAlreadyExistsException;
import com.alianza.practica.domain.exceptions.UserNotFoundBySharedKeyException;
import com.alianza.practica.model.dto.CustomerDTO;
import com.alianza.practica.model.dto.GeneralResponseDTO;
import com.alianza.practica.model.dto.GetCustomerRequestDTO;
import com.alianza.practica.model.dto.SaveCustomerRequestDTO;
import com.alianza.practica.service.interfaces.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController implements ICustomerController {

    private final ICustomerService customerService;
    @Override
    public ResponseEntity<GeneralResponseDTO<CustomerDTO>> consultBySharedKey(GetCustomerRequestDTO request) throws UserNotFoundBySharedKeyException {
        return ResponseEntity.ok(customerService.consultBySharedKey(request));
    }
    @Override
    public ResponseEntity<GeneralResponseDTO<String>> saveCustomer(SaveCustomerRequestDTO request) throws UserAlreadyExistsException {
        return ResponseEntity.ok(customerService.saveCustomer(request));
    }
    @Override
    public ResponseEntity<GeneralResponseDTO<List<CustomerDTO>>> getAllClients() {
        return ResponseEntity.ok(customerService.getAllClients());
    }
}
