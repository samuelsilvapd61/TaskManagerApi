package silva.oliveira.samuel.taskmanager.domain.response;

import lombok.Builder;
import lombok.Data;
import silva.oliveira.samuel.taskmanager.domain.entity.TaskStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskResponse {

  private Long id;
  private String name;
  private String description;
  private TaskStatus taskStatus;
  private LocalDateTime createDate;
  private LocalDateTime finishDate;

}
