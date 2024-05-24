package demo.devspringboot.WebBackEnd.product.restResoucre;

import demo.devspringboot.WebBackEnd.common.util.ResponseUtil;
import demo.devspringboot.WebBackEnd.product.dto.ProductDTO;
import demo.devspringboot.WebBackEnd.product.dto.ProductDTOForSave;
import demo.devspringboot.WebBackEnd.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/product")
public class ProductRestResoucre {

    private ProductService productService ;

    public ProductRestResoucre (ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/all")
    public Object findAll(){
        return ResponseUtil.get(productService.findAll(ProductDTO.class), HttpStatus.OK);
    }

    @GetMapping("/findbyid")
    public Object findById(@RequestParam UUID id){
        return ResponseUtil.get(productService.findById(id, ProductDTO.class), HttpStatus.OK);
    }

    @PostMapping("/save")
    public Object save(@RequestBody ProductDTOForSave productDTO){
        return ResponseUtil.get(productService.save(productDTO), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public Object deleteById(@RequestParam UUID id){
        productService.delete(id);
        return ResponseUtil.get("Delete Successfully.", HttpStatus.OK);
    }


}
