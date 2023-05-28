package ru.coda.traineeship.rating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.coda.traineeship.rating.model.RatingMentor;
import ru.coda.traineeship.rating.model.RatingTrainee;
import ru.coda.traineeship.recruitment.model.Resume;

import java.util.List;

@Repository("RatingTraineeRepository")
public interface RatingTraineeRepository extends JpaRepository<RatingTrainee, Long> {

    @Query("SELECT r.grade, r.review FROM RatingTrainee AS r WHERE r.resume.id = :id")
    List<RatingTrainee> ratingTrainee(@Param("id") Integer id);

    @Modifying
    RatingMentor save(RatingTrainee review);
    @Query("SELECT r.resume, AVG(r.grade) FROM RatingTrainee AS r GROUP BY r.resume ORDER BY r.grade DESC")
    List<Resume> ratingTrainee();
}
