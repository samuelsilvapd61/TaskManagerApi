package silva.oliveira.samuel.taskmanager.domain.request;

import lombok.Data;
import silva.oliveira.samuel.taskmanager.domain.entity.TaskStatus;

@Data
public class TaskUpdateRequest {

  private String name;
  private String description;
  private TaskStatus taskStatus;

}
