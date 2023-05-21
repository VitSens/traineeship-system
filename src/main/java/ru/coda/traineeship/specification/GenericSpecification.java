package ru.coda.traineeship.specification;

import java.util.Objects;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class GenericSpecification<T> implements Specification<T> {

  private final SearchCriteria searchCriteria;

  public GenericSpecification(final SearchCriteria searchCriteria){
    super();
    this.searchCriteria = searchCriteria;
  }

  @Override
  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

    String strToSearch = searchCriteria.getValue().toString().toLowerCase();

    switch(Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))){
      case CONTAINS:
        return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

      case DOES_NOT_CONTAIN:
        return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

      case BEGINS_WITH:
        return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

      case DOES_NOT_BEGIN_WITH:
        return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

      case ENDS_WITH:
        return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

      case DOES_NOT_END_WITH:
        return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch.toLowerCase());

      case EQUAL:
        return cb.equal(cb.lower(root.get(searchCriteria.getFilterKey())), String.valueOf(searchCriteria.getValue()).toLowerCase());

      case NOT_EQUAL:
        return cb.notEqual(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

      case NUL:
        return cb.isNull(root.get(searchCriteria.getFilterKey()));

      case NOT_NULL:
        return cb.isNotNull(root.get(searchCriteria.getFilterKey()));

      case GREATER_THAN:
        return cb.greaterThan(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

      case GREATER_THAN_EQUAL:
        return cb.greaterThanOrEqualTo(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

      case LESS_THAN:
        return cb.lessThan(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

      case LESS_THAN_EQUAL:
        return cb.lessThanOrEqualTo(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
    }
    return null;
  }
}
