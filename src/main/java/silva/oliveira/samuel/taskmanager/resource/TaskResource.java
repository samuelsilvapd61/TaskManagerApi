package silva.oliveira.samuel.taskmanager.resource;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import silva.oliveira.samuel.taskmanager.domain.request.TaskRequest;
import silva.oliveira.samuel.taskmanager.domain.request.TaskUpdateRequest;
import silva.oliveira.samuel.taskmanager.domain.response.TaskResponse;
import silva.oliveira.samuel.taskmanager.service.TaskService;

import java.util.List;

/**
 * Classe referente às tarefas que recebe as requisições externas.
 */
@RestController
@RequestMapping("/task")
public class TaskResource {

  @Autowired
  private TaskService taskService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void add(@Valid @RequestBody TaskRequest request) {
    taskService.addTask(request);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<TaskResponse>> getAll(
      @RequestHeader Long id,
      @PageableDefault Pageable page
  ) {
    //chama o service passando o page
    return ResponseEntity.ok().body(taskService.getTasks(id, page));
  }

  @PatchMapping
  @ResponseStatus(HttpStatus.OK)
  public void update(
      @RequestParam Long id,
      @Valid @RequestBody TaskUpdateRequest request
  ) {
    taskService.updateTask(id, request);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public void update(@RequestParam Long id) {
    taskService.deleteTask(id);
  }

}
