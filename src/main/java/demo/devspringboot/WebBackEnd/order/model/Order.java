package demo.devspringboot.WebBackEnd.order.model;

import demo.devspringboot.WebBackEnd.common.model.BaseEntity;
import demo.devspringboot.WebBackEnd.product.model.Product;
import demo.devspringboot.WebBackEnd.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = OrderEntity.TABLE_NAME)
public class Order extends BaseEntity {
    @ManyToOne
    @JoinTable(
            name = OrderEntity.Order_User.TABLE_NAME,
            joinColumns = @JoinColumn(name = OrderEntity.Order_User.ORDER_ID),
            inverseJoinColumns = @JoinColumn(name = OrderEntity.Order_User.USER_ID)
    )
    private User user;

    @ManyToMany
    @JoinTable(
            name = OrderEntity.Order_Product.TABLE_NAME,
            joinColumns = @JoinColumn(name = OrderEntity.Order_Product.ORDER_ID),
            inverseJoinColumns = @JoinColumn(name = OrderEntity.Order_Product.PRODUCT_ID)
    )
    public Set<Product> products = new HashSet<>();
}
