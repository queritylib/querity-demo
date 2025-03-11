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
    Query q = query != null ?
        // parse query language
        QuerityParser.parseQuery(query) :
        // just an empty query if null
        Query.builder().build();
    if(!q.hasPagination()) {
      // modify the query to include pagination if missing
      q = q.toBuilder()
          .pagination(1, 50)
          .build();
    } else if (q.getPagination().getPageSize() > 50) {
      throw new IllegalArgumentException("Pagination limit exceeded, max page size is 50");
    }
    List<OrderDto> items = querity.findAll(Order.class, q).stream()
        .map(orderMapper::toDto)
        .toList();
    Long totalCount = querity.count(Order.class, q.getFilter());
    return new Result<>(items, totalCount);
  }
}
