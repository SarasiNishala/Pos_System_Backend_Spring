package lk.ijse.gdse68.nike_spring.service;

import lk.ijse.gdse68.nike_spring.customObj.CustomerResponse;
import lk.ijse.gdse68.nike_spring.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    String saveCustomer(CustomerDTO customerDTO);
    void updateCustomer(String id, CustomerDTO customerDTO);
    void deleteCustomer(String id);
    CustomerResponse getCustomerById(String id);
    List<CustomerDTO> getAllCustomers();
}
