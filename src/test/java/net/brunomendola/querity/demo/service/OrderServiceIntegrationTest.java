package net.brunomendola.querity.demo.service;

import net.brunomendola.querity.demo.dto.OrderDto;
import net.brunomendola.querity.demo.util.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class OrderServiceIntegrationTest {

  @Autowired
  private OrderService orderService;

  @Test
  void testSimpleQuery_idIn1And2() {
    // Given - data is seeded by DatabaseSeeder from orders.json
    String query = "id in (1, 2)";

    // When
    Result<OrderDto> result = orderService.findOrders(query);

    // Then
    assertThat(result.items()).hasSize(2);
    assertThat(result.totalCount()).isEqualTo(2L);

    // Verify the orders have the expected order numbers based on seeded data
    assertThat(result.items())
      .extracting(OrderDto::getOrderNumber)
      .containsExactlyInAnyOrder(1L, 2L);
  }

  @Test
  void testAdvancedQuery_selectOrderNumberWhereIdIn1And2() {
    // Given - data is seeded by DatabaseSeeder from orders.json
    String query = "select orderNumber where id in (1, 2)";

    // When
    Result<OrderDto> result = orderService.findOrders(query);

    // Then
    assertThat(result.items()).hasSize(2);
    assertThat(result.totalCount()).isEqualTo(2L);

    // Verify only orderNumber field is projected
    assertThat(result.items())
      .extracting(OrderDto::getOrderNumber)
      .containsExactlyInAnyOrder(1L, 2L);

    // Verify that only orderNumber is populated (other fields should be null or default)
    result.items().forEach(order -> {
      assertThat(order.getOrderNumber()).isNotNull();
    });
  }
}
