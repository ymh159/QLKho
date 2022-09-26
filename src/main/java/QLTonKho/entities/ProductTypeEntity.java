package QLTonKho.entities;

import QLTonKho.constants.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProductTypeEntity {
  @JsonProperty(Constants._ID)
  private String id;
  private String name;
  private String description;
}
