package demo.devspringboot.WebBackEnd.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String productName;
    private String productDescription;
    private Double ProductPrice;


}
