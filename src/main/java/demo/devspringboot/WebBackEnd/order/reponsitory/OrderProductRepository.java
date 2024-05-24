package demo.devspringboot.WebBackEnd.order.reponsitory;

import demo.devspringboot.WebBackEnd.order.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
    @Query("SELECT op FROM OrderProduct op WHERE op.order.id = :orderId AND op.product.id = :productId")
    Optional <OrderProduct> findByProductIdAndOrderId(UUID productId, UUID orderId);
}

