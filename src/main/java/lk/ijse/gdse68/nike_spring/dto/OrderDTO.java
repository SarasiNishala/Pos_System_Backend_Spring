package lk.ijse.gdse68.nike_spring.dto;

import jakarta.validation.Valid;
import lk.ijse.gdse68.nike_spring.customObj.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements Serializable , OrderResponse {
    private String orderId;
    private String customerId;
    @Valid
    private List<OrderDetailDTO> orderDetails;
    private LocalDateTime orderDateTime;
    private double totalPrice;
}
