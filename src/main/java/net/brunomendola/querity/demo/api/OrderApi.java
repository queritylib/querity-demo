package net.brunomendola.querity.demo.api;

import net.brunomendola.querity.demo.dto.OrderDto;
import net.brunomendola.querity.demo.service.OrderService;
import net.brunomendola.querity.demo.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderApi {

  private final OrderService service;

  public OrderApi(OrderService service) {
    this.service = service;
  }

  @GetMapping
  public Result<OrderDto> findOrders(@RequestParam("q") String query) {
    return service.findOrders(query);
  }
}
