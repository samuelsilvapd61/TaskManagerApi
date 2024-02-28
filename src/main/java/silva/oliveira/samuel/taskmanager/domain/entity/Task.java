package silva.oliveira.samuel.taskmanager.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import silva.oliveira.samuel.taskmanager.utils.TaskStatusEnum;

import java.time.LocalDateTime;

/**
 * Classe que representa a tarefa.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity(name = "task")
public class Task {

  @Id
  private Long id;
  private String name;
  private String description;
  private TaskStatusEnum status = TaskStatusEnum.TO_DO;
  private LocalDateTime createDate;
  private LocalDateTime finishDate;
  private User taskOwner;

}
