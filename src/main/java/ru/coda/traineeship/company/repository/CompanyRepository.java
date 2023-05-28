package ru.coda.traineeship.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.coda.traineeship.company.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
