package QLTonKho.entities;

import QLTonKho.constants.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data

@RequiredArgsConstructor
public class ProductEntity {
  @JsonProperty(Constants._ID)
  private String id;
  private String name;
  private int value;
  private int price;
  @JsonProperty(Constants.TYPE)
  private ProductTypeEntity type;
}
