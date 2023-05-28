package ru.coda.traineeship.rating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.coda.traineeship.company.model.Company;
import ru.coda.traineeship.rating.model.RatingCompany;
import ru.coda.traineeship.rating.model.RatingMentor;

import java.util.List;

@Repository("RatingCompanyRepository")
public interface RatingCompanyRepository extends JpaRepository<RatingCompany, Long> {
    @Query("SELECT r.grade, r.review FROM RatingCompany AS r WHERE r.company.id = :id")
    List<RatingCompany> ratingCompany(@Param("id") Integer id);

    @Modifying
    RatingMentor save(RatingCompany review);
    @Query("SELECT r.company, AVG(r.grade) FROM RatingCompany AS r GROUP BY r.company ORDER BY r.grade DESC")
    List<Company> ratingCompany();
}
