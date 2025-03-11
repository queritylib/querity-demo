package net.brunomendola.querity.demo;

import net.brunomendola.querity.demo.model.Order;
import net.brunomendola.querity.demo.model.OrderRepository;
import net.brunomendola.querity.demo.util.JsonUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DatabaseSeeder implements InitializingBean {

  private static final String TEST_DATA_PATH = "/data/orders.json";

  private final OrderRepository orderRepository;

  protected DatabaseSeeder(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    seedDatabase();
  }

  private void seedDatabase() throws IOException {
    List<Order> entities = JsonUtils.readListFromJson(TEST_DATA_PATH, getEntityClass());
    saveEntities(entities);
  }

  protected void saveEntities(List<Order> entities) {
    entities
        .forEach(o -> o.getRows()
            .forEach(r -> r.setOrder(o)));
    orderRepository.saveAll(entities);
    orderRepository.flush();
  }

  protected Class<Order> getEntityClass() {
    return Order.class;
  }
}
