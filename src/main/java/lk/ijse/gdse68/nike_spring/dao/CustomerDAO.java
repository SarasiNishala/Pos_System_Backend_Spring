package lk.ijse.gdse68.nike_spring.dao;

import lk.ijse.gdse68.nike_spring.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<CustomerEntity, String> {}
