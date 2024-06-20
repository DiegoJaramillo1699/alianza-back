package com.alianza.practica.service.interfaces;

import com.alianza.practica.domain.exceptions.UserAlreadyExistsException;
import com.alianza.practica.domain.exceptions.UserNotFoundBySharedKeyException;
import com.alianza.practica.model.dto.CustomerDTO;
import com.alianza.practica.model.dto.GeneralResponseDTO;
import com.alianza.practica.model.dto.GetCustomerRequestDTO;
import com.alianza.practica.model.dto.SaveCustomerRequestDTO;

import java.util.List;

public interface ICustomerService {

    public GeneralResponseDTO<CustomerDTO> consultBySharedKey(GetCustomerRequestDTO request) throws UserNotFoundBySharedKeyException;
    public GeneralResponseDTO<String> saveCustomer(SaveCustomerRequestDTO request) throws UserAlreadyExistsException;
    public GeneralResponseDTO<List<CustomerDTO>> getAllClients();

}
