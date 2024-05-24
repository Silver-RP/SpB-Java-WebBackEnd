package demo.devspringboot.WebBackEnd.order.service;

import demo.devspringboot.WebBackEnd.common.service.GenericService;
import demo.devspringboot.WebBackEnd.order.dto.OrderDTO;
import demo.devspringboot.WebBackEnd.order.dto.OrderDTOForSave;
import demo.devspringboot.WebBackEnd.order.model.Order;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface OrderService extends GenericService <Order, OrderDTO, UUID> {

    List<OrderDTO> findByUsername(String username);
    OrderDTO save(OrderDTOForSave orderDTOForSave);
    OrderDTO addProducts(UUID orderID, Map<UUID, Integer> productQuantity);
    OrderDTO removeProducts(UUID orderID, Map<UUID, Integer> productQuantity);
}