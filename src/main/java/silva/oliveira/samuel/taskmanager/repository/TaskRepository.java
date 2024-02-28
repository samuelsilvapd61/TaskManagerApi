package silva.oliveira.samuel.taskmanager.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import silva.oliveira.samuel.taskmanager.domain.entity.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findAllByTaskOwnerId(Long id, Pageable page);
}
