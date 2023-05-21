package ru.coda.traineeship.specification;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import ru.coda.traineeship.recruitment.model.Resume;

public class SpecificationBuilder {

  private final List<SearchCriteria> params;

  public SpecificationBuilder(){
    this.params = new ArrayList<>();
  }

  public final SpecificationBuilder with(String key, String operation, Object value){
    params.add(new SearchCriteria(key, operation, value));
    return this;
  }

  public final SpecificationBuilder with(SearchCriteria searchCriteria){
    params.add(searchCriteria);
    return this;
  }

  public Specification<Resume> build(){
    if(params.size() == 0){
      return null;
    }

    Specification<Resume> result = new GenericSpecification<>(params.get(0));
    for (int idx = 1; idx < params.size(); idx++){
      SearchCriteria criteria = params.get(idx);
      result = SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL
          ? Specification.where(result).and(new GenericSpecification<>(criteria))
          : Specification.where(result).or(new GenericSpecification<>(criteria));
    }

    return result;
  }

}
