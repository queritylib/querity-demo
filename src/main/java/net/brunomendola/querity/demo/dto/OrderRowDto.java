package net.brunomendola.querity.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderRowDto {
  private Long rowNumber;
  private String sku;
  private String description;
  private Long quantity;
  private BigDecimal unitPrice;
  private BigDecimal totalPrice;
}
