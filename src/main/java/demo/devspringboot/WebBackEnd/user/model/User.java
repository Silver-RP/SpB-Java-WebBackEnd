package demo.devspringboot.WebBackEnd.user.model;

import demo.devspringboot.WebBackEnd.common.model.BaseEntity;
import demo.devspringboot.WebBackEnd.order.model.Order;
import demo.devspringboot.WebBackEnd.order.model.OrderEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@Entity
@Table(name = UserEntity.TABLE_NAME)
    // Defines the structure of the user table in database (@Entity)
    // DTO between different layers of the application.
public class User extends BaseEntity {

    @Column(name = UserEntity.USERNAME)
    private String username;

    @Column(name = UserEntity.PASSWORD)
    private String password;

    @Column(name = UserEntity.AGE)
    private int age;

    @Column(name = UserEntity.GENDER)
    private String gender;

    @Column(name = UserEntity.ROLES)
    private String roles;

    private  String avatar;

    @OneToMany(mappedBy = OrderEntity.Order_User.ORDER_MAPPED_USER)
    private Set<Order> orders = new HashSet<>();
}
