package net.brunomendola.querity.demo.service;

import net.brunomendola.querity.api.Querity;
import net.brunomendola.querity.api.Query;
import net.brunomendola.querity.demo.dto.OrderDto;
import net.brunomendola.querity.demo.mapper.OrderMapper;
import net.brunomendola.querity.demo.model.Order;
import net.brunomendola.querity.demo.util.Result;
import net.brunomendola.querity.parser.QuerityParser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
  private final Querity querity;
  private final OrderMapper orderMapper;

  public OrderService(Querity querity, OrderMapper orderMapper) {
    this.querity = querity;
    this.orderMapper = orderMapper;
  }

  public Result<OrderDto> findOrders(String query) {
    Query q = QuerityParser.parseQuery(query);
    List<OrderDto> items = querity.findAll(Order.class, q).stream()
        .map(orderMapper::toDto)
        .toList();
    Long totalCount = querity.count(Order.class, q.getFilter());
    return new Result<>(items, totalCount);
  }
}
