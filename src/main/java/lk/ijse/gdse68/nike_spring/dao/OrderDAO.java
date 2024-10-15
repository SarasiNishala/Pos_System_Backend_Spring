package lk.ijse.gdse68.nike_spring.dao;

import lk.ijse.gdse68.nike_spring.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<OrderEntity, String> {}
