package demo.devspringboot.WebBackEnd.product.service;

import demo.devspringboot.WebBackEnd.common.util.WebBackEndMapper;
import demo.devspringboot.WebBackEnd.product.dto.ProductDTO;
import demo.devspringboot.WebBackEnd.product.dto.ProductDTOForSave;
import demo.devspringboot.WebBackEnd.product.model.Product;
import demo.devspringboot.WebBackEnd.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ProductServiceImpl implements ProductService  {

    private final WebBackEndMapper webBackEndMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(WebBackEndMapper webBackEndMapper, ProductRepository productRepository){
        this.webBackEndMapper = webBackEndMapper;
        this.productRepository = productRepository;
   }

    @Override
    public JpaRepository<Product, UUID> getRepository() {
        return productRepository;
    }

    @Override
    public WebBackEndMapper getMapper() {
        return webBackEndMapper;
    }

    @Override
    public ProductDTO save(ProductDTOForSave productDTOForSave) {
        Product product = productRepository.save(webBackEndMapper.map(productDTOForSave, Product.class));
        return webBackEndMapper.map(product, ProductDTO.class);
    }
}
