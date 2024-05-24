package demo.devspringboot.WebBackEnd.order.service;

import demo.devspringboot.WebBackEnd.common.exception.WBEBussinessException;
import demo.devspringboot.WebBackEnd.common.util.WebBackEndMapper;
import demo.devspringboot.WebBackEnd.order.dto.OrderDTO;
import demo.devspringboot.WebBackEnd.order.dto.OrderDTOForSave;
import demo.devspringboot.WebBackEnd.order.model.Order;
import demo.devspringboot.WebBackEnd.order.model.OrderProduct;
import demo.devspringboot.WebBackEnd.order.reponsitory.OrderProductRepository;
import demo.devspringboot.WebBackEnd.order.reponsitory.OrderResponsitory;
import demo.devspringboot.WebBackEnd.product.model.Product;
import demo.devspringboot.WebBackEnd.product.repository.ProductRepository;
import demo.devspringboot.WebBackEnd.user.model.User;
import demo.devspringboot.WebBackEnd.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Override
    public List<OrderDTO> findByUsername(String username) {
        return orderResponsitory.findByUsername(username)
                .stream().map(order -> mapper.map(order, OrderDTO.class)).toList();
    }

    private  final OrderResponsitory orderResponsitory ;
    private  final ProductRepository productRepository;
    private  final WebBackEndMapper mapper;
    private  final UserRepository userRepository;
    private final OrderProductRepository orderProductRepository;

    public OrderServiceImpl (OrderResponsitory orderResponsitory, ProductRepository productRepository, UserRepository userRepository , WebBackEndMapper mapper, OrderProductRepository orderProductRepository){
        this.orderResponsitory = orderResponsitory;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderDTO save(OrderDTOForSave orderDTOForSave) {
        // Check User
        User user = userRepository.findByUsername(orderDTOForSave.getUsername())
                .orElseThrow(()-> new WBEBussinessException("User not found."));
        // Create productList
        Set <OrderProduct> orderProductSet = new LinkedHashSet<>();
        final BigDecimal[] totalPrice = {BigDecimal.ZERO};
        Order newOrder = new Order();

        HashMap<UUID, Integer> productQuantityMap =orderDTOForSave.getProductQuantityList();
        Set<UUID> productIdsSet = productQuantityMap.keySet(); // method keySet() return a UUID list

        //Check productIds
        productIdsSet.forEach(
                productIds ->{
                    Optional <Product> productOptional = productRepository.findById(productIds);

                    if(productOptional.isEmpty())return;

                    //new Product
                    Product product = productOptional.get();
                    int quantity = productQuantityMap.get(productIds);
                    OrderProduct orderProduct = OrderProduct.builder()
                                                    .product(product)
                                                    .order(newOrder)
                                                    .quantity(quantity)
                                                    .build();
                    orderProductSet.add(orderProduct); // ?
                    product.getOrdersProduct().add(orderProduct); // ?

                    //Calculate price
                    totalPrice[0] = totalPrice[0].add(BigDecimal.valueOf(product.getProductPrice())
                            .multiply(BigDecimal.valueOf(quantity))
                    );
                }
        );
        // Prevent saving products that do not exist.
        if(orderProductSet.isEmpty()) throw new WBEBussinessException("All inputted product is not found.");
            //Set newOrder
        newOrder.setUser(user);
        newOrder.setTotalPrice(totalPrice[0]);
        newOrder.setOrderProducts(orderProductSet);
        return mapper.map(orderResponsitory.save(newOrder), OrderDTO.class);
    }

    @Override
    public OrderDTO addProducts(UUID orderID, Map<UUID, Integer> productQuantity) {
        //Check Order
        Order order = orderResponsitory.findById(orderID)
                .orElseThrow(()-> new WBEBussinessException("Order not found."));
        //Check productIds
        productQuantity.keySet().forEach((productId)->{
            Optional <Product> productOptional = productRepository.findById(productId);
            if(productOptional.isEmpty())return;
            Product product = productOptional.get();
            int quantity = productQuantity.get(productId);
            Optional <OrderProduct>  orderProductOptional = orderProductRepository.findByProductIdAndOrderId(productId, orderID);
            OrderProduct orderProduct;
            if (orderProductOptional.isEmpty()){
                orderProduct =  OrderProduct.builder()
                        .quantity(quantity)
                        .product(product)
                        .order(order)
                        .build();
            }else {
                orderProduct = orderProductOptional.get();
                orderProduct.setQuantity(orderProduct.getQuantity() + quantity);
            }
            product.getOrdersProduct().add(orderProduct);
            order.getOrderProducts().add(orderProduct);
            order.setTotalPrice(order.getTotalPrice()
                    .add(
                            BigDecimal.valueOf(product.getProductPrice())
                                    .multiply(BigDecimal.valueOf(quantity))
                    )
            );
        });
        return mapper.map(orderResponsitory.save(order), OrderDTO.class);
    }

    @Override
    public OrderDTO removeProducts(UUID orderID, Map<UUID, Integer> productQuantity) {
        Order order = orderResponsitory.findById(orderID)
                .orElseThrow(()-> new WBEBussinessException("Order not found."));
        productQuantity.keySet().forEach((productId)->{
            Optional <Product> productOptional = productRepository.findById(productId);
            if(productOptional.isEmpty())return;
            Product product = productOptional.get();
            int quantity = productQuantity.get(productId);
            Optional <OrderProduct> orderProductOptional = orderProductRepository
                                                                .findByProductIdAndOrderId(productId, orderID);
            if (orderProductOptional.isEmpty())return;
            OrderProduct orderProduct = orderProductOptional.get();
            if(orderProduct.getQuantity() > quantity){
                orderProduct.setQuantity(orderProduct.getQuantity() - quantity);
                order.setTotalPrice(order.getTotalPrice()
                        .subtract(
                                BigDecimal.valueOf(product.getProductPrice())
                                        .multiply(BigDecimal.valueOf(quantity))
                        )
                );
            }else{
                product.getOrdersProduct().remove(orderProduct);
                order.getOrderProducts().remove(orderProduct);
                order.setTotalPrice(order.getTotalPrice()
                        .subtract(
                                BigDecimal.valueOf(product.getProductPrice())
                                        .multiply(BigDecimal.valueOf(orderProduct.getQuantity()))
                        )
                );
            }
        });
        return mapper.map(orderResponsitory.save(order), OrderDTO.class);
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
