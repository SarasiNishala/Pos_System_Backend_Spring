package lk.ijse.gdse68.nike_spring.dto;

import lk.ijse.gdse68.nike_spring.customObj.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements Serializable , ItemResponse {
    private String itemCode;
    private String itemName;
    private String size;
    private double unitPrice;
    private int quantity;
}
