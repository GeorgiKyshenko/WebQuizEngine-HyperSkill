package engine.repositories;

import engine.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaQuizRepository extends JpaRepository<Quiz, Long>, PagingAndSortingRepository<Quiz, Long> {
}
