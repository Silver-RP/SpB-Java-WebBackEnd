package demo.devspringboot.WebBackEnd.order.model;


import demo.devspringboot.WebBackEnd.product.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = OrderEntity.Order_Product.TABLE_NAME)
public class OrderProduct {

    @Id
    @GeneratedValue // self initialize
    private UUID id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Order order;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Product product;

    private int quantity;
    
}
