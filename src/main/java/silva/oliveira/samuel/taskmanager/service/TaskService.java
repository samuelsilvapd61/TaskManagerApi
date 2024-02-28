package silva.oliveira.samuel.taskmanager.service;

import org.springframework.data.domain.Pageable;
import silva.oliveira.samuel.taskmanager.domain.request.TaskRequest;
import silva.oliveira.samuel.taskmanager.domain.request.TaskUpdateRequest;
import silva.oliveira.samuel.taskmanager.domain.response.TaskResponse;

import java.util.List;

public interface TaskService {

  List<TaskResponse> getTasks(Long id, Pageable page);
  void addTask(TaskRequest request);
  void updateTask(Long id, TaskUpdateRequest request);
  void deleteTask(Long id);

}
