package net.brunomendola.querity.demo.mapper;

import net.brunomendola.querity.demo.dto.CustomerInfoDto;
import net.brunomendola.querity.demo.dto.OrderDto;
import net.brunomendola.querity.demo.dto.OrderRowDto;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface OrderProjectionMapper {
  @MapMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
  OrderDto toDto(Map<String, Object> projection);

  @SuppressWarnings("unchecked")
  default CustomerInfoDto objToCustomerInfoDto(Object projection) {
    return toCustomerInfoDto((Map<String, Object>) projection);
  }

  @MapMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
  CustomerInfoDto toCustomerInfoDto(Map<String, Object> projection);

  @SuppressWarnings("unchecked")
  default CustomerInfoDto.AddressDto objToAddressDto(Object projection) {
    return toAddressDto((Map<String, Object>) projection);
  }

  @MapMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
  CustomerInfoDto.AddressDto toAddressDto(Map<String, Object> projection);

  @SuppressWarnings("unchecked")
  default List<OrderRowDto> mapOrderRowDtoList(Object projection) {
    return ((List<Map<String, Object>>) projection).stream()
      .map(this::toOrderRowDto)
      .toList();
  }

  @MapMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
  OrderRowDto toOrderRowDto(Map<String, Object> projection);

  default Long castingMapLong(Object source) {
    return (Long) source;
  }

  default String castingMapString(Object source) {
    return (String) source;
  }

  default ZonedDateTime castingMapZonedDateTime(Object source) {
    return (ZonedDateTime) source;
  }

  default BigDecimal castingMapBigDecimal(Object source) {
    return (BigDecimal) source;
  }
}
