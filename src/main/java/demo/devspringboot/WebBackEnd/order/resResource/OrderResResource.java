package demo.devspringboot.WebBackEnd.order.resResource;

import demo.devspringboot.WebBackEnd.common.util.ResponseUtil;
import demo.devspringboot.WebBackEnd.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderResResource {

    OrderService orderService;
    public OrderResResource (OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public Object save(@RequestParam String username){
        return ResponseUtil.get(orderService.save(username), HttpStatus.OK);
    }
}
