package silva.oliveira.samuel.taskmanager.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import silva.oliveira.samuel.taskmanager.converter.TaskConverter;
import silva.oliveira.samuel.taskmanager.domain.request.TaskRequest;
import silva.oliveira.samuel.taskmanager.domain.request.TaskUpdateRequest;
import silva.oliveira.samuel.taskmanager.domain.response.TaskResponse;
import silva.oliveira.samuel.taskmanager.repository.TaskRepository;
import silva.oliveira.samuel.taskmanager.service.TaskService;
import silva.oliveira.samuel.taskmanager.service.UserService;

import java.util.List;

/**
 * Classe referente à tarefa que aplica regras de negócio e faz comunicação com o repositório.
 */
@Service
public class TaskServiceImp implements TaskService {

  @Autowired
  private TaskRepository taskRepository;
  @Autowired
  private TaskConverter taskConverter;
  @Autowired
  private UserService userService;

  @Override
  public List<TaskResponse> getTasks(Long taskOwnerId, Pageable page) {
    var list = taskRepository.findAllByTaskOwnerId(taskOwnerId, page);
    List<TaskResponse> responseList = list.stream().map(task -> taskConverter.entityToResponse(task)).toList();
    return responseList;
  }

  @Override
  public void addTask(TaskRequest request) {
    var user = userService.getUserById(request.getTaskOwnerId());
    var task = taskConverter.buildNewTask(request);
    taskRepository.save(task);
  }

  @Override
  public void updateTask(Long id, TaskUpdateRequest request) {
    var oldTask = taskRepository.findById(id);
    if (oldTask.isPresent()) {
      var updatedTask = taskConverter.buildUpdatedTask(oldTask.get(), request);
      taskRepository.save(updatedTask);
    } else {
      throw new RuntimeException("Essa tarefa não existe");
    }

  }

  @Override
  public void deleteTask(Long id) {
    var task = taskRepository.findById(id);
    if (task.isPresent()) {
      taskRepository.deleteById(id);
    } else {
      throw new RuntimeException("Essa tarefa não existe");
    }

  }

}
