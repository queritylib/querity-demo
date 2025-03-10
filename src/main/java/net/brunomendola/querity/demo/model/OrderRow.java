package net.brunomendola.querity.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "order")
public class OrderRow extends AbstractPersistable<Long> {
  @ManyToOne
  @JsonIgnore
  private Order order;
  private Long rowNumber;
  private String sku;
  private String description;
  private Long quantity;
  private BigDecimal unitPrice;
  private BigDecimal totalPrice;
}
