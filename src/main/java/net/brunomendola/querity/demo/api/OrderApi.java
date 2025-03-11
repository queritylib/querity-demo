package net.brunomendola.querity.demo.api;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
  public Result<OrderDto> findOrders(@RequestParam(name = "q", required = false)
                                     @Parameter(description = "Query language supports filtering, sorting, and pagination. Pagination is automatically applied if not present, max page size is 50. See examples below.",
                                         examples = {
                                         @ExampleObject(name="findByOrderNumber", summary = "find single order by order number",
                                             value = "orderNumber=500"),
                                         @ExampleObject(name="placedAfterDate", summary = "orders placed since 1st July 2024, first page of max 10 results",
                                             value = "placementDate>=\"2024-07-01T00:00:00.000Z\" page 1,10"),
                                         @ExampleObject(name="shippedToCountry", summary = "orders shipped to Italy",
                                             value = "shippingCustomer.address.country=\"Italy\""),
                                         @ExampleObject(name="withExpensiveRows", summary = "orders with rows priced over 1000 Euros, sorted by placement date in descending order, first page of max 20 results", value =
                                             "distinct and(rows.totalPrice>1000,currency=\"EUR\") sort by placementDate desc page 1,20"),
                                     })
                                     String query) {
    return service.findOrders(query);
  }
}
