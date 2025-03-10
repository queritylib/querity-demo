package net.brunomendola.querity.demo.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class CustomerInfo extends AbstractPersistable<Long> {
  private String firstName;
  private String lastName;
  private String email;
  @Embedded
  private Address address;

  @Embeddable
  @Getter
  @Setter
  @EqualsAndHashCode
  @ToString
  public static class Address {
    private String streetAddress;
    private String city;
    private String postalCode;
    private String country;
  }
}
