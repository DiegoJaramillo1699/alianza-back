package com.alianza.practica.service.implementations;

import com.alianza.practica.domain.exceptions.UserAlreadyExistsException;
import com.alianza.practica.domain.exceptions.UserNotFoundBySharedKeyException;
import com.alianza.practica.model.dto.CustomerDTO;
import com.alianza.practica.model.dto.GeneralResponseDTO;
import com.alianza.practica.model.dto.GetCustomerRequestDTO;
import com.alianza.practica.model.dto.SaveCustomerRequestDTO;
import com.alianza.practica.model.entities.CustomerEntity;
import com.alianza.practica.model.repositories.CustomerRepository;
import com.alianza.practica.service.interfaces.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public GeneralResponseDTO<CustomerDTO> consultBySharedKey(GetCustomerRequestDTO request) throws UserNotFoundBySharedKeyException {
        System.out.println(request);
        CustomerEntity customerEntity = customerRepository.findBySharedKey(request.getSharedKey())
            .orElseThrow(() -> new UserNotFoundBySharedKeyException("User not found with specified sharedKey"));

        CustomerDTO customerDTO = CustomerDTO.builder()
                .sharedKey(customerEntity.getSharedKey())
                .businessId(customerEntity.getBusinessId())
                .email(customerEntity.getEmail())
                .phone(customerEntity.getPhone())
                .createdAt(customerEntity.getCreatedAt())
                .build();

        GeneralResponseDTO<CustomerDTO> responseDTO = new GeneralResponseDTO<>();
        responseDTO.setResponse(customerDTO);
        responseDTO.setStatusCode(0);
        responseDTO.setMessage("Operation Performed Successfully");

        return responseDTO;
    }

    @Override
    public GeneralResponseDTO<String> saveCustomer(SaveCustomerRequestDTO request) throws UserAlreadyExistsException {


        boolean customerValidation = customerRepository.findBySharedKey(request.getSharedKey()).isPresent();

        if(customerValidation){
            throw new UserAlreadyExistsException("User with shared key already exists");
        }

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerEntity customerEntity = modelMapper.map(request,CustomerEntity.class);

        GeneralResponseDTO<String> generalResponseDTO = new GeneralResponseDTO<>();
        generalResponseDTO.setResponse(customerRepository.save(customerEntity).getSharedKey());
        generalResponseDTO.setMessage("Customer saved successfully");
        generalResponseDTO.setStatusCode(0);
        return generalResponseDTO;

    }

    @Override
    public GeneralResponseDTO<List<CustomerDTO>> getAllClients() {
        GeneralResponseDTO<List<CustomerDTO>> generalResponseDTO = new GeneralResponseDTO<>();
        generalResponseDTO.setStatusCode(0);
        generalResponseDTO.setMessage("Operation Performed Successfully");

        /*List<CustomerEntity> entityList = customerRepository.findAll();
        Type listType = new TypeToken<List<CustomerDTO>>(){}.getType();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE); */
        List<CustomerDTO> customerDTOList = customerEntitytoCustomerDto(customerRepository.findAll());
        generalResponseDTO.setResponse(customerDTOList);

        return generalResponseDTO;
    }

    private List<CustomerDTO> customerEntitytoCustomerDto(List<CustomerEntity> entityList){
        List<CustomerDTO> customerDTOList = new ArrayList<>();
            entityList.forEach(entity ->{
                CustomerDTO customerDTO = CustomerDTO.builder()
                        .sharedKey(entity.getSharedKey())
                        .businessId(entity.getBusinessId())
                        .email(entity.getEmail())
                        .phone(entity.getPhone())
                        .createdAt(entity.getCreatedAt())
                        .build();
                customerDTOList.add(customerDTO);

            });
        return customerDTOList;
    }
}
