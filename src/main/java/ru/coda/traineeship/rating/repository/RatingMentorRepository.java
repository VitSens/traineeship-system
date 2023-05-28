package ru.coda.traineeship.rating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.coda.traineeship.rating.model.Mentor;
import ru.coda.traineeship.rating.model.RatingMentor;

import java.util.List;

@Repository("RatingMentorRepository")
public interface RatingMentorRepository extends JpaRepository<RatingMentor, Long> {

    @Query("SELECT r.grade, r.review FROM RatingMentor AS r WHERE r.mentor.id = :id")
    List<RatingMentor> ratingMentor(@Param("id") Integer id);

    @Modifying
    RatingMentor save(RatingMentor review);

    @Query("SELECT r.mentor, AVG(r.grade) FROM RatingMentor AS r GROUP BY r.mentor ORDER BY r.grade DESC")
    List<Mentor> ratingMentor();

}
