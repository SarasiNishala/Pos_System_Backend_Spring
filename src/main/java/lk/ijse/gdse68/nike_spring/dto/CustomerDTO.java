package lk.ijse.gdse68.nike_spring.dto;

import lk.ijse.gdse68.nike_spring.customObj.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements Serializable, CustomerResponse {
    private String customerId;
    private String customerName;
    private String address;
    private String email;
    private int phone;
}
