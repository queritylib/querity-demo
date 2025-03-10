package net.brunomendola.querity.demo.mapper;

import net.brunomendola.querity.demo.dto.OrderDto;
import net.brunomendola.querity.demo.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
  OrderDto toDto(Order order);
}
