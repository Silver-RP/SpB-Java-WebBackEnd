package demo.devspringboot.WebBackEnd.order.dto;

import lombok.*;

import java.util.HashMap;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderDTOForSave {

    private String  username;
    private HashMap <UUID, Integer> productQuantityList;

}
