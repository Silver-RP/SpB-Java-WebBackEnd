package demo.devspringboot.WebBackEnd.order.service;

import demo.devspringboot.WebBackEnd.common.service.GenericService;
import demo.devspringboot.WebBackEnd.order.dto.OrderDTO;
import demo.devspringboot.WebBackEnd.order.model.Order;

import java.util.UUID;

public interface OrderService extends GenericService <Order, OrderDTO, UUID> {
    OrderDTO save(String username);
}