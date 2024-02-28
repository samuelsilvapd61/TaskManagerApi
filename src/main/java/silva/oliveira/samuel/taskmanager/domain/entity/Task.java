package silva.oliveira.samuel.taskmanager.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Classe que representa a tarefa.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "task")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private TaskStatus taskStatus;
  private LocalDateTime createDate;
  private LocalDateTime finishDate;
  private Long taskOwnerId;

}
