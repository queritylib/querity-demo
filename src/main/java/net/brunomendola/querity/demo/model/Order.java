package net.brunomendola.querity.demo.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Entity
@Table(name = "`order`")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "rows")
public class Order extends AbstractPersistable<Long> {
  private Long orderNumber;
  private ZonedDateTime placementDate;
  private String ipAddress;
  private Currency currency;
  @JoinColumn(name = "billing_customer_id", referencedColumnName = "id")
  @OneToOne(cascade = CascadeType.ALL)
  private CustomerInfo billingCustomer;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "shipping_customer_id", referencedColumnName = "id")
  private CustomerInfo shippingCustomer;
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderRow> rows = new ArrayList<>();
  private BigDecimal totalPrice;
}
