package demo.devspringboot.WebBackEnd.product.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//DTO and DTOForSave should have the same name.
public class ProductDTOForSave {

    private String name;
    private String description;
    private Double price;

}
