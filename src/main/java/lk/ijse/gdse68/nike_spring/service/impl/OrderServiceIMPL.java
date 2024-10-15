package lk.ijse.gdse68.nike_spring.service.impl;

import lk.ijse.gdse68.nike_spring.dao.ItemDAO;
import lk.ijse.gdse68.nike_spring.dao.OrderDAO;
import lk.ijse.gdse68.nike_spring.dto.OrderDTO;
import lk.ijse.gdse68.nike_spring.dto.OrderDetailDTO;
import lk.ijse.gdse68.nike_spring.entity.ItemEntity;
import lk.ijse.gdse68.nike_spring.entity.OrderDetailsEntitiy;
import lk.ijse.gdse68.nike_spring.entity.OrderEntity;
import lk.ijse.gdse68.nike_spring.excption.DataPersistFailedException;
import lk.ijse.gdse68.nike_spring.excption.ItemNotFoundException;
import lk.ijse.gdse68.nike_spring.service.OrderService;
import lk.ijse.gdse68.nike_spring.util.DateTimeUtil;
import lk.ijse.gdse68.nike_spring.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceIMPL implements OrderService {
    @Autowired
    private final OrderDAO orderDAO;

    @Autowired
    private final ItemDAO itemDAO;

    @Autowired
    private final MappingUtil mappingUtil;
    @Override
    public String saveOrder(OrderDTO orderDTO) {
        orderDTO.setOrderId(generateOrderID());
        orderDTO.setCustomerId(orderDTO.getCustomerId());
        orderDTO.setOrderDateTime(DateTimeUtil.getCurrentDateTime());
        orderDTO.setTotalPrice(orderDTO.getOrderDetails().stream().mapToDouble(detail -> detail.getQuantity() * detail.getUnitPrice()).sum());
        OrderEntity orderEntity = mappingUtil.convertToOrderEntity(orderDTO);

        List<OrderDetailsEntitiy> orderDetailEntities = orderDTO.getOrderDetails().stream().map(detail -> {
                    OrderDetailsEntitiy orderDetailEntity = mappingUtil.convertToOrderDetailEntity(detail);
                    orderDetailEntity.setOrder(orderEntity);
                    return orderDetailEntity;
                })
                .collect(Collectors.toList());

        orderEntity.setOrderDetails(orderDetailEntities);
        boolean allItemsUpdated = orderDTO.getOrderDetails().stream().allMatch(this::updateItemQty);

        if (allItemsUpdated) {
            orderDAO.save(orderEntity);
            return "Order placed successfully";
        } else {
            throw new DataPersistFailedException("place order failed");
        }
    }

    private boolean updateItemQty(OrderDetailDTO orderDetailDTO) {
        ItemEntity item = itemDAO.findById(orderDetailDTO.getItemCode()).orElse(null);
        if (item == null) {
            throw new ItemNotFoundException("Item not found");
        }

        if (item.getQuantity() < orderDetailDTO.getQuantity()) {
            throw new ItemNotFoundException("Item qty not enough");
        }

        item.setQuantity(item.getQuantity() - orderDetailDTO.getQuantity());
        itemDAO.save(item);
        return true;
    }

    private String generateOrderID() {
        if (orderDAO.count() == 0) {
            return "O001";
        } else {
            String lastId = orderDAO.findAll().get(orderDAO.findAll().size() - 1).getOrderId();
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "O00" + newId;
            } else if (newId < 100) {
                return "O0" + newId;
            } else {
                return "O" + newId;
            }
        }
    }
}
