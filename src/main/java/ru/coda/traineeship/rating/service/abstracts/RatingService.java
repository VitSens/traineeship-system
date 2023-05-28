package ru.coda.traineeship.rating.service.abstracts;

import org.springframework.data.repository.query.Param;
import ru.coda.traineeship.company.model.Company;
import ru.coda.traineeship.rating.model.Mentor;
import ru.coda.traineeship.rating.model.RatingCompany;
import ru.coda.traineeship.rating.model.RatingMentor;
import ru.coda.traineeship.rating.model.RatingTrainee;
import ru.coda.traineeship.recruitment.model.Resume;

import java.util.List;

public interface RatingService {

    List<RatingCompany> ratingCompany(Integer id);

    List<RatingTrainee> ratingTrainee(Integer id);

    List<RatingMentor> ratingMentor(Integer id);

    List<Company> ratingCompany();

    List<Resume> ratingTrainee();

    List<Mentor> ratingMentor();

    void saveMentorReview(RatingMentor review);

    void saveTraineeReview(RatingTrainee review);

    void saveCompanyReview(RatingCompany review);
}
