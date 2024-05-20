package demo.devspringboot.WebBackEnd.product.restResoucre;

import demo.devspringboot.WebBackEnd.common.util.ResponseUtil;
import demo.devspringboot.WebBackEnd.product.dto.ProductDTO;
import demo.devspringboot.WebBackEnd.product.model.Product;
import demo.devspringboot.WebBackEnd.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("")
public class ProductRestResoucre {

    private ProductService productService ;

    public ProductRestResoucre (ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/all")
    public Object findAll(){
        return ResponseUtil.get(productService.findAll(ProductDTO.class), HttpStatus.OK);
    }

    @PostMapping("/save")
    public Object save(@RequestBody ProductDTO productDTO){
        return ResponseUtil.get(productService.save(productDTO, Product.class, ProductDTO.class), HttpStatus.OK);
    }
}
