package silva.oliveira.samuel.taskmanager.domain.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import silva.oliveira.samuel.taskmanager.utils.TaskStatusEnum;

/**
 * Classe de request da tarefa.
 */
@Data
public class TaskRequest {

  @NotEmpty
  private String name;
  @NotEmpty
  private String description;
  private TaskStatusEnum status;
  @NotNull
  private Long taskOwnerId;

}
