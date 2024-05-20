package demo.devspringboot.WebBackEnd.order.dto;

import demo.devspringboot.WebBackEnd.product.model.Product;
import demo.devspringboot.WebBackEnd.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private UUID id;
    private UserDTO user;
    private Set<Product>products;
}
