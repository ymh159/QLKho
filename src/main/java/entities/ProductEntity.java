package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data

@RequiredArgsConstructor
public class ProductEntity {
  @JsonProperty("_id")
  private String id;
  private String name;
  private String value;
  private String price;
  @JsonProperty("type")
  private ProductTypeEntity type;
}
