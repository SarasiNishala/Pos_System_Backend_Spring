package lk.ijse.gdse68.nike_spring.dao;

import lk.ijse.gdse68.nike_spring.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDAO extends JpaRepository<ItemEntity, String> {}
