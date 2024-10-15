package lk.ijse.gdse68.nike_spring.dto;

import lk.ijse.gdse68.nike_spring.customObj.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDTO implements Serializable , OrderResponse {
    private String orderId;
    private String itemCode;
    private String itemName;
    private String size;
    private double unitPrice;
    private int quantity;
    private double total;
}
