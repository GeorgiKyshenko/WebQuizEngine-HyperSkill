package engine.repositories;

import engine.models.CompletedTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompletedTaskRepository extends JpaRepository<CompletedTask, Long>, PagingAndSortingRepository<CompletedTask, Long> {

    Page<CompletedTask> findCompletedTaskByUserId(long id, Pageable pageable);
}
