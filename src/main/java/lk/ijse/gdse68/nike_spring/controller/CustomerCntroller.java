package lk.ijse.gdse68.nike_spring.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse68.nike_spring.customObj.CustomerResponse;
import lk.ijse.gdse68.nike_spring.dto.CustomerDTO;
import lk.ijse.gdse68.nike_spring.excption.CustomerNotFoundException;
import lk.ijse.gdse68.nike_spring.excption.DataPersistFailedException;
import lk.ijse.gdse68.nike_spring.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerCntroller {
    @Autowired
    private CustomerService customerService;

    static Logger logger = LoggerFactory.getLogger(CustomerCntroller.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@Valid @RequestBody CustomerDTO customer) {
        if (customer == null) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                customerService.saveCustomer(customer);
                logger.info("Customer saved : " + customer);
                return ResponseEntity.created(null).build();
            } catch (DataPersistFailedException e) {
                return ResponseEntity.badRequest().build();
            } catch (Exception e) {
                logger.error(e.getMessage());
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerResponse getCustomerById(@PathVariable("customerId") String customerId) {
        return customerService.getCustomerById(customerId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable("customerId") String customerId) {
        if (customerDTO == null || customerId == null) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                customerService.updateCustomer(customerId, customerDTO);
                logger.info("Customer updated : " + customerDTO);
                return ResponseEntity.noContent().build();
            } catch (CustomerNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                logger.error(e.getMessage());
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId) {
        try {
            customerService.deleteCustomer(customerId);
            logger.info("Customer deleted : " + customerId);
            return ResponseEntity.noContent().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
