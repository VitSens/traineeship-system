package ru.coda.traineeship.specification;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDto {

  private List<SearchCriteria> searchCriteriaList;
  private String dataOption;

}
