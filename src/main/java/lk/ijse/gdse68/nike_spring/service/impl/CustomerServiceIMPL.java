package lk.ijse.gdse68.nike_spring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.nike_spring.customObj.CustomerResponse;
import lk.ijse.gdse68.nike_spring.customObj.impl.CustomerErrorResponse;
import lk.ijse.gdse68.nike_spring.dao.CustomerDAO;
import lk.ijse.gdse68.nike_spring.dto.CustomerDTO;
import lk.ijse.gdse68.nike_spring.entity.CustomerEntity;
import lk.ijse.gdse68.nike_spring.excption.CustomerNotFoundException;
import lk.ijse.gdse68.nike_spring.service.CustomerService;
import lk.ijse.gdse68.nike_spring.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private final CustomerDAO customerDAO;

    @Autowired
    private final MappingUtil mappingUtil;
    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setCustomerId(generateCustomerID());
        customerDTO.setCustomerName(customerDTO.getCustomerName());
        customerDTO.setAddress(customerDTO.getAddress());
        customerDTO.setEmail(customerDTO.getEmail());
        customerDTO.setPhone(customerDTO.getPhone());
        CustomerEntity customerEntity = mappingUtil.convertToCustomerEntity(customerDTO);
        customerDAO.save(customerEntity);
        System.out.println("Customer saved : " + customerEntity);
        return "Customer saved";
    }

    @Override
    public void updateCustomer(String id, CustomerDTO customerDTO) {
        Optional<CustomerEntity> tmpCustomer = customerDAO.findById(id);
        if (!tmpCustomer.isPresent()) {
            System.out.println("Customer not found");
            throw new CustomerNotFoundException("Customer not found");
        }else {
            tmpCustomer.get().setName(customerDTO.getCustomerName());
            tmpCustomer.get().setAddress(customerDTO.getAddress());
            tmpCustomer.get().setEmail(customerDTO.getEmail());
            tmpCustomer.get().setPhone(customerDTO.getPhone());
            System.out.println("Customer updated : " + customerDTO);
        }
    }

    @Override
    public void deleteCustomer(String id) {
        if (customerDAO.existsById(id)) {
            customerDAO.deleteById(id);
            System.out.println("Customer deleted : " + id);
        } else {
            System.out.println("Customer not found");
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    @Override
    public CustomerResponse getCustomerById(String id) {
        if (customerDAO.existsById(id)) {
            CustomerEntity customerEntity = customerDAO.getReferenceById(id);
            System.out.println("Customer found : " + customerEntity);
            CustomerDTO customerDTO = mappingUtil.convertToCustomerDTO(customerEntity);
            customerDTO.setCustomerName(customerDTO.getCustomerName());
            return customerDTO;
        }else {
            System.out.println("Customer not found");
            return new CustomerErrorResponse(0,"Customer not found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return mappingUtil.convertToCustomerDTOList(customerDAO.findAll());
    }

    private String generateCustomerID() {
        if (customerDAO.count() == 0) {
            return "C001";
        } else {
            String lastId = customerDAO.findAll().get(customerDAO.findAll().size() - 1).getCustomerId();
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "C00" + newId;
            } else if (newId < 100) {
                return "C0" + newId;
            } else {
                return "C" + newId;
            }
        }
    }
}
