package demo.devspringboot.WebBackEnd.order.model;

import demo.devspringboot.WebBackEnd.common.model.BaseEntity;
import demo.devspringboot.WebBackEnd.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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

    @OneToMany(mappedBy = OrderEntity.Order_Product.ORDER_MAPPED_ORDER_PRODUCT,
                            cascade = {CascadeType.MERGE, CascadeType.PERSIST})

    private Set<OrderProduct> orderProducts = new HashSet<>();
    private BigDecimal totalPrice;
}
