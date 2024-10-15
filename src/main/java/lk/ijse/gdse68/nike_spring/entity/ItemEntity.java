package lk.ijse.gdse68.nike_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "items")
@Entity
public class ItemEntity implements Serializable {
    @Id
    private String itemCode;
    private String itemName;
    private String size;
    private double unitPrice;
    private int quantity;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderDetailsEntitiy> orderDetails;
}
