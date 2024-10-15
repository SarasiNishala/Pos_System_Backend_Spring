package lk.ijse.gdse68.nike_spring.service.impl;

import lk.ijse.gdse68.nike_spring.customObj.ItemResponse;
import lk.ijse.gdse68.nike_spring.dao.ItemDAO;
import lk.ijse.gdse68.nike_spring.dto.ItemDTO;
import lk.ijse.gdse68.nike_spring.entity.ItemEntity;
import lk.ijse.gdse68.nike_spring.excption.ItemNotFoundException;
import lk.ijse.gdse68.nike_spring.service.ItemService;
import lk.ijse.gdse68.nike_spring.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private final ItemDAO itemDAO;

    @Autowired
    private final MappingUtil mappingUtil;
    @Override
    public String saveItem(ItemDTO itemDTO) {
        itemDTO.setItemCode(generateItemID());
        itemDTO.setItemName(itemDTO.getItemName());
        itemDTO.setSize(itemDTO.getSize());
        itemDTO.setUnitPrice(itemDTO.getUnitPrice());
        itemDTO.setQuantity(itemDTO.getQuantity());
        ItemEntity itemEntity = mappingUtil.convertToItemEntity(itemDTO);
        itemDAO.save(itemEntity);
        System.out.println("saveItem: " + itemDTO);
        return "Item saved";
    }

    @Override
    public void updateItem(String id, ItemDTO itemDTO) {
        Optional<ItemEntity> tmpItem = itemDAO.findById(id);
        if (!tmpItem.isPresent()) {
            System.out.println("Item not found");
            throw new ItemNotFoundException("Item not found");
        } else {
            tmpItem.get().setItemName(itemDTO.getItemName());
            tmpItem.get().setSize(itemDTO.getSize());
            tmpItem.get().setUnitPrice(itemDTO.getUnitPrice());
            tmpItem.get().setQuantity(itemDTO.getQuantity());
            System.out.println("updateItem: " + itemDTO);
        }

    }

    @Override
    public void deleteItem(String id) {
        if (itemDAO.existsById(id)) {
            itemDAO.deleteById(id);
            System.out.println("Item deleted : " + id);
        } else {
            System.out.println("Item not found");
            throw new ItemNotFoundException("Item not found");
        }
    }

    @Override
    public ItemResponse getItemById(String id) {
        if (itemDAO.existsById(id)) {
            return mappingUtil.convertToItemDTO(itemDAO.getReferenceById(id));
        } else {
            throw new ItemNotFoundException("Item not found");
        }
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return mappingUtil.convertToItemDTOList(itemDAO.findAll());
    }

    private String generateItemID() {
        if (itemDAO.count() == 0) {
            return "I001";
        } else {
            String lastId = itemDAO.findAll().get(itemDAO.findAll().size() - 1).getItemCode();
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "I00" + newId;
            } else if (newId < 100) {
                return "I0" + newId;
            } else {
                return "I" + newId;
            }
        }
    }
}
