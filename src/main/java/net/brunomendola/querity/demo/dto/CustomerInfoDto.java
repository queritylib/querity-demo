package net.brunomendola.querity.demo.dto;

import lombok.Data;

@Data
public class CustomerInfoDto {
  private String firstName;
  private String lastName;
  private String email;
  private CustomerInfoDto.AddressDto address;

  @Data
  public static class AddressDto {
    private String streetAddress;
    private String city;
    private String postalCode;
    private String country;
  }
}
