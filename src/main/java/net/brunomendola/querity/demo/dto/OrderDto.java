package net.brunomendola.querity.demo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Data
public class OrderDto {
  private Long orderNumber;
  private ZonedDateTime placementDate;
  private String ipAddress;
  private Currency currency;
  private CustomerInfoDto billingCustomer;
  private CustomerInfoDto shippingCustomer;
  private List<OrderRowDto> rows = new ArrayList<>();
  private BigDecimal totalPrice;

}
