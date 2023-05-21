package ru.coda.traineeship.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.coda.traineeship.quiz.model.Quiz;

@Repository("QuizRepository")
public interface QuizRepository extends PagingAndSortingRepository<Quiz, Long> {

	Page<Quiz> findByIsPublishedTrue(Pageable pageable);

	@Query("select q from Quiz q where q.name like %?1%")
	Page<Quiz> searchByName(String name, Pageable pageable);
}
