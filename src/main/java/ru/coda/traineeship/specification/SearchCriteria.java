package ru.coda.traineeship.specification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {

  private String filterKey;
  private Object value;
  private String operation;
  private String dataOption;

  public SearchCriteria(String key, String operation, Object value) {
    super();
    this.filterKey = key;
    this.operation = operation;
    this.value = value;
  }
}
