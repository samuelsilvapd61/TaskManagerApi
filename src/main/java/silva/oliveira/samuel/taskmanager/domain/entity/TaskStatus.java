package silva.oliveira.samuel.taskmanager.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import silva.oliveira.samuel.taskmanager.utils.enums.TaskStatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TaskStatus {

  private TaskStatusEnum status = TaskStatusEnum.TO_DO;
  private String comment = "";

}
