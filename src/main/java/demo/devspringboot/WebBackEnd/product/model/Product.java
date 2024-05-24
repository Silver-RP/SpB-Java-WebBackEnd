package demo.devspringboot.WebBackEnd.product.model;

import demo.devspringboot.WebBackEnd.common.model.BaseEntity;
import demo.devspringboot.WebBackEnd.order.model.OrderEntity;
import demo.devspringboot.WebBackEnd.order.model.OrderProduct;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = ProductEntity.TABLE_NAME)
public class Product extends BaseEntity {
    @Column (name = ProductEntity.PRODUCT_NAME)
    private String productName;

    @Column (name = ProductEntity.PRODUCT_DESCRIPTION)
    private String productDescription;

    @Column (name = ProductEntity.PRODUCT_PRICE)
    private Double ProductPrice;

    @OneToMany (mappedBy = OrderEntity.Order_Product.PRODUCT_MAPPED_ORDER_PRODUCT,
                            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<OrderProduct> ordersProduct = new HashSet<>();
}
