package net.brunomendola.querity.demo.service;

import io.github.queritylib.querity.api.AdvancedQuery;
import io.github.queritylib.querity.api.Querity;
import io.github.queritylib.querity.api.Query;
import io.github.queritylib.querity.api.QueryDefinition;
import io.github.queritylib.querity.parser.QuerityParser;
import net.brunomendola.querity.demo.dto.OrderDto;
import net.brunomendola.querity.demo.mapper.OrderMapper;
import net.brunomendola.querity.demo.mapper.OrderProjectionMapper;
import net.brunomendola.querity.demo.model.Order;
import net.brunomendola.querity.demo.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
  private final Querity querity;
  private final OrderMapper orderMapper;
  private final OrderProjectionMapper orderProjectionMapper;

  public OrderService(Querity querity, OrderMapper orderMapper, OrderProjectionMapper orderProjectionMapper) {
    this.querity = querity;
    this.orderMapper = orderMapper;
    this.orderProjectionMapper = orderProjectionMapper;
  }

  @Transactional(readOnly = true)
  public Result<OrderDto> findOrders(String query) {
    QueryDefinition q = query != null ?
      // parse query language
      QuerityParser.parseQuery(query) :
      // just an empty query if null
      Query.builder().build();
    q = fixPagination(q);
    List<OrderDto> items = getResults(q);
    Long totalCount = querity.count(Order.class, q.getFilter());
    return new Result<>(items, totalCount);
  }

  private List<OrderDto> getResults(QueryDefinition q) {
    if (q instanceof Query simpleQuery) {
      return querity.findAll(Order.class, simpleQuery).stream()
        .map(orderMapper::toDto)
        .toList();
    } else if (q instanceof AdvancedQuery advancedQuery) {
      return querity.findAllProjected(Order.class, advancedQuery).stream()
        .map(orderProjectionMapper::toDto)
        .toList();
    }
    throw new IllegalStateException("Unsupported QueryDefinition type: " + q.getClass());
  }

  /**
   * Modify the query to include pagination if missing.
   *
   * @param q the query
   * @return the modified query with pagination
   */
  private static QueryDefinition fixPagination(QueryDefinition q) {
    if (!q.hasPagination()) {
      if (q instanceof Query queryObj)
        q = queryObj.toBuilder()
          .pagination(1, 50)
          .build();
      else if (q instanceof AdvancedQuery advancedQueryObj)
        q = advancedQueryObj.toBuilder()
          .pagination(1, 50)
          .build();
    } else if (q.getPagination().getPageSize() > 50) {
      throw new IllegalArgumentException("Pagination limit exceeded, max page size is 50");
    }
    return q;
  }
}
