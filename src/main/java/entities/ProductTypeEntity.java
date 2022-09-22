package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProductTypeEntity {
  private String _id;
  private String typeId;
  private String name;
  private String description;
}
