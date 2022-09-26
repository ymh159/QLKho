package QLTonKho.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data

@RequiredArgsConstructor
public class InventoryDTO {
  private String productID;
  private String productName;
  private String typeName;
  private int value;
  private int price;
  private String description;

  public InventoryDTO(String productID, String productName, String typeName, int value, int price,
      String description) {
    this.productID = productID;
    this.productName = productName;
    this.typeName = typeName;
    this.value = value;
    this.price = price;
    this.description = description;
  }
}
