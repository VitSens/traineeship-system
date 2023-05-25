package ru.coda.traineeship.configure.specification;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import ru.coda.traineeship.recruitment.model.Resume;

public class SpecificationBuilder<T> {

  private final List<SearchCriteria> params;

  public SpecificationBuilder(){
    this.params = new ArrayList<>();
  }

  public SpecificationBuilder with(String key, String operation, Object value, String prefix, String suffix) {

    SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
    if (op != null) {
      if (op == SearchOperation.EQUALITY) {
        boolean startWithAsterisk = prefix.contains("*");
        boolean endWithAsterisk = suffix.contains("*");

        if (startWithAsterisk && endWithAsterisk) {
          op = SearchOperation.CONTAINS;
        } else if (startWithAsterisk) {
          op = SearchOperation.ENDS_WITH;
        } else if (endWithAsterisk) {
          op = SearchOperation.STARTS_WITH;
        }
      }
      params.add(new SearchCriteria(key, op, value));
    }
    return this;
  }

  public Specification<T> build(){
    if (params.size() == 0) {
      return null;
    }

    Specification<T> result = new GenericSpecification<>(params.get(0));

    for (int i = 1; i < params.size(); i++) {
        result = Specification.where(result).and(new GenericSpecification<>(params.get(i)));
    }

    return result;
  }

}
