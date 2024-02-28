package silva.oliveira.samuel.taskmanager.converter;

import org.springframework.stereotype.Component;
import silva.oliveira.samuel.taskmanager.domain.entity.Task;
import silva.oliveira.samuel.taskmanager.domain.request.TaskRequest;
import silva.oliveira.samuel.taskmanager.domain.request.TaskUpdateRequest;
import silva.oliveira.samuel.taskmanager.domain.response.TaskResponse;

import java.time.LocalDateTime;

@Component
public class TaskConverter {

  public TaskResponse entityToResponse(Task task) {
    return TaskResponse.builder()
        .id(task.getId())
        .name(task.getName())
        .description(task.getDescription())
        .taskStatus(task.getTaskStatus())
        .createDate(task.getCreateDate())
        .finishDate(task.getFinishDate())
        .build();
  }

  public Task buildNewTask(TaskRequest request) {
    return Task.builder()
        .name(request.getName())
        .description(request.getDescription())
        .taskStatus(request.getTaskStatus())
        .createDate(LocalDateTime.now())
        .finishDate(null)
        .taskOwnerId(request.getTaskOwnerId())
        .build();
  }

  public Task buildUpdatedTask(Task oldTask, TaskUpdateRequest updatedTask) {
    return Task.builder()
        .id(oldTask.getId())
        .name(updatedTask.getName().isEmpty() ? oldTask.getName() : updatedTask.getName())
        .description(updatedTask.getDescription().isEmpty() ? oldTask.getDescription() : updatedTask.getDescription())
        .taskStatus(updatedTask.getTaskStatus() == null ? oldTask.getTaskStatus() : updatedTask.getTaskStatus())
        .createDate(oldTask.getCreateDate())
        .finishDate(oldTask.getFinishDate())
        .taskOwnerId(oldTask.getTaskOwnerId())
        .build();
  }

}
