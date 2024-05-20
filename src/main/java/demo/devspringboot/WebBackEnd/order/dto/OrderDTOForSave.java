package demo.devspringboot.WebBackEnd.order.dto;

import demo.devspringboot.WebBackEnd.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDTOForSave {
    private UserDTO user;

}
