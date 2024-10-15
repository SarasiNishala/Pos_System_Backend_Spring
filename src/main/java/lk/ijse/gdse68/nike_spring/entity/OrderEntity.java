package lk.ijse.gdse68.nike_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
@Entity
public class OrderEntity implements Serializable {
    @Id
    private String orderId;
    @ManyToOne
    @JoinColumn(name = "customer_id" , referencedColumnName = "customerId")
    private CustomerEntity customerEntity;
    private LocalDateTime orderDateTime;
    private double totalPrice;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<OrderDetailsEntitiy> orderDetails;
}
