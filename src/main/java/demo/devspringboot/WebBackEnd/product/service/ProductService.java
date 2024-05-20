package demo.devspringboot.WebBackEnd.product.service;

import demo.devspringboot.WebBackEnd.common.service.GenericService;
import demo.devspringboot.WebBackEnd.product.dto.ProductDTO;
import demo.devspringboot.WebBackEnd.product.model.Product;

import java.util.UUID;

public interface ProductService extends GenericService<Product, ProductDTO, UUID> {

}
