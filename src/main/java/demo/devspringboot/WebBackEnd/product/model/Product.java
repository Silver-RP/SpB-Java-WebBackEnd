package demo.devspringboot.WebBackEnd.product.model;

import demo.devspringboot.WebBackEnd.common.model.BaseEntity;
import demo.devspringboot.WebBackEnd.order.model.Order;
import demo.devspringboot.WebBackEnd.order.model.OrderEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
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

    @ManyToMany(mappedBy = OrderEntity.Order_Product.ORDER_MAPPED_PRODUCT)
    private Set<Order> orders = new HashSet<>();
}
