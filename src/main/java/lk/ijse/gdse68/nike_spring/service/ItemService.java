package lk.ijse.gdse68.nike_spring.service;


import lk.ijse.gdse68.nike_spring.customObj.ItemResponse;
import lk.ijse.gdse68.nike_spring.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    String saveItem(ItemDTO itemDTO);
    void updateItem(String id, ItemDTO itemDTO);
    void deleteItem(String id);
    ItemResponse getItemById(String id);
    List<ItemDTO> getAllItems();
}
