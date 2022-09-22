package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
//@RequiredArgsConstructor

public class ProductEntity {
  private String _id;
  private String productID;
  private String name;
  private String value;
  private String price;
//  private String typeId;
  @JsonProperty("typeId")
  private ProductTypeEntity typeId;

//  public ProductEntity(String _id, String productID, String name, String value, String price,
//      String typeId) {
//    this._id = _id;
//    this.productID = productID;
//    this.name = name;
//    this.value = value;
//    this.price = price;
//    this.typeId = typeId;
//  }
}
