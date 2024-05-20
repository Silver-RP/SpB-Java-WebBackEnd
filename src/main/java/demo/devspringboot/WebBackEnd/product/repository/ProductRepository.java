package demo.devspringboot.WebBackEnd.product.repository;

import demo.devspringboot.WebBackEnd.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> { //JpaRepository<Product, UUID> recieves a model and a model of id
    Product save(Product product);
}
