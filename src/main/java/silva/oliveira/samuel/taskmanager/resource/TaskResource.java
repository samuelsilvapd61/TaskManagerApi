package silva.oliveira.samuel.taskmanager.resource;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import silva.oliveira.samuel.taskmanager.domain.request.TaskRequest;

/**
 * Classe referente às tarefas que recebe as requisições externas.
 */
@RestController
@RequestMapping("/task")
public class TaskResource {

  @PostMapping
  public String addTask(@Valid @RequestBody TaskRequest request) {
    return "Adicionando task";
  }

}
