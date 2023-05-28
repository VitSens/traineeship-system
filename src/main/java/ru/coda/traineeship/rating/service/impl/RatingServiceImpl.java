package ru.coda.traineeship.rating.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coda.traineeship.company.model.Company;
import ru.coda.traineeship.rating.model.Mentor;
import ru.coda.traineeship.rating.model.RatingCompany;
import ru.coda.traineeship.rating.model.RatingMentor;
import ru.coda.traineeship.rating.model.RatingTrainee;
import ru.coda.traineeship.rating.repository.RatingCompanyRepository;
import ru.coda.traineeship.rating.repository.RatingMentorRepository;
import ru.coda.traineeship.rating.repository.RatingTraineeRepository;
import ru.coda.traineeship.rating.service.abstracts.RatingService;
import ru.coda.traineeship.recruitment.model.Resume;

import java.util.List;

@Service("RatingServiceImpl")
@Transactional
public class RatingServiceImpl implements RatingService {
    private RatingCompanyRepository ratingCompanyRepository;
    private RatingTraineeRepository ratingTraineeRepository;
    private RatingMentorRepository ratingMentorRepository;

    public RatingServiceImpl(RatingCompanyRepository ratingCompanyRepository, RatingTraineeRepository ratingTraineeRepository, RatingMentorRepository ratingMentorRepository) {
        this.ratingCompanyRepository = ratingCompanyRepository;
        this.ratingTraineeRepository = ratingTraineeRepository;
        this.ratingMentorRepository = ratingMentorRepository;
    }

    @Override
    public List<RatingCompany> ratingCompany(Integer id) {
        return ratingCompanyRepository.ratingCompany(id);
    }

    @Override
    public List<RatingTrainee> ratingTrainee(Integer id) {
        return ratingTraineeRepository.ratingTrainee(id);
    }

    @Override
    public List<RatingMentor> ratingMentor(Integer id) {
        return ratingMentorRepository.ratingMentor(id);
    }

    @Override
    public List<Company> ratingCompany() {
        return ratingCompanyRepository.ratingCompany();
    }

    @Override
    public List<Resume> ratingTrainee() {
        return ratingTraineeRepository.ratingTrainee();
    }

    @Override
    public List<Mentor> ratingMentor() {
        return ratingMentorRepository.ratingMentor();
    }

    @Override
    public void saveMentorReview(RatingMentor review) {
        ratingMentorRepository.save(review);
    }

    @Override
    public void saveTraineeReview(RatingTrainee review) {
        ratingTraineeRepository.save(review);
    }

    @Override
    public void saveCompanyReview(RatingCompany review) {
        ratingCompanyRepository.save(review);
    }
}
