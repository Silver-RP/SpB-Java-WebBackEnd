package demo.devspringboot.WebBackEnd.order.dto;

import demo.devspringboot.WebBackEnd.user.dto.UserDTO;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private UUID id;
    private UserDTO user;
    private Set<OrderProductDTO>products;
    private BigDecimal totalPrice;
}
