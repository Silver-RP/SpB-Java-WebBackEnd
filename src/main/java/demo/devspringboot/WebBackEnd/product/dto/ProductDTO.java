package demo.devspringboot.WebBackEnd.product.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private UUID id;
    private String productName;
    private String productDescription;
    private Double ProductPrice;

}
