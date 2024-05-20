package demo.devspringboot.WebBackEnd.order.service;

import demo.devspringboot.WebBackEnd.common.exception.WBEBussinessException;
import demo.devspringboot.WebBackEnd.common.util.WebBackEndMapper;
import demo.devspringboot.WebBackEnd.order.dto.OrderDTO;
import demo.devspringboot.WebBackEnd.order.model.Order;
import demo.devspringboot.WebBackEnd.order.reponsitory.OrderResponsitory;
import demo.devspringboot.WebBackEnd.user.model.User;
import demo.devspringboot.WebBackEnd.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    OrderResponsitory orderResponsitory ;
    WebBackEndMapper mapper;
    UserRepository userRepository;

    public OrderServiceImpl (OrderResponsitory orderResponsitory, UserRepository userRepository ,WebBackEndMapper mapper){
        this.orderResponsitory = orderResponsitory;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public OrderDTO save(String username) {
        Optional <User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()){
            Order newOrder = new Order();
            newOrder.setUser(userOptional.get());
            orderResponsitory.save(newOrder);
            return mapper.map(newOrder, OrderDTO.class);
        }else throw new WBEBussinessException("User not found.") ;
    }

    @Override
    public JpaRepository<Order, UUID> getRepository() {
        return orderResponsitory;
    }

    @Override
    public WebBackEndMapper getMapper() {
        return mapper;
    }


}
