package demo.devspringboot.WebBackEnd.order.resResource;

import demo.devspringboot.WebBackEnd.common.util.ResponseUtil;
import demo.devspringboot.WebBackEnd.order.dto.OrderDTO;
import demo.devspringboot.WebBackEnd.order.dto.OrderDTOForSave;
import demo.devspringboot.WebBackEnd.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderResResource {

    OrderService orderService;
    public OrderResResource (OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/findAll")
    public Object findAll(){
        return ResponseUtil.get(orderService.findAll(OrderDTO.class), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public Object findAll(@RequestParam UUID id){
        return ResponseUtil.get(orderService.findById(id, OrderDTO.class), HttpStatus.OK);
    }

    @GetMapping("/findByUsername")
    public Object findAll(@RequestParam String username){
        return ResponseUtil.get(orderService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/save")
    public Object save(@RequestBody OrderDTOForSave dtoForSave){
        return ResponseUtil.get(orderService.save(dtoForSave), HttpStatus.OK);
    }

    @PostMapping("/{order-id}/addProducts")
    public Object addProducts(@PathVariable ("order-id") UUID orderID, @RequestBody Map<UUID, Integer> productQuantity){
        return ResponseUtil.get(orderService.addProducts(orderID, productQuantity), HttpStatus.OK);
    }

    @PostMapping("/{order-id}/removeProducts")
    public Object removeProducts(@PathVariable ("order-id") UUID orderID, @RequestBody Map<UUID, Integer> productQuantity){
        return ResponseUtil.get(orderService.removeProducts(orderID, productQuantity), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public Object deleteById(@RequestParam UUID id){
        orderService.delete(id);
        return ResponseUtil.get("Delete Successfully.", HttpStatus.OK);
    }

}
