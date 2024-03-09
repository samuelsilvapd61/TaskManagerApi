package silva.oliveira.samuel.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import silva.oliveira.samuel.taskmanager.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { }
