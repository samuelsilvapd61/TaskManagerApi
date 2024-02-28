package silva.oliveira.samuel.taskmanager.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Classe que representa os estados possíveis da tarefa.
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum {

  TO_DO("A tarefa ainda não foi iniciada."),
  IN_PROGRESS("A tarefa está sendo realizada."),
  WAITING("A tarefa está aguardando algo ou alguém antes de progredir."),
  COMPLETED("A tarefa foi concluída."),
  CANCELLED("A tarefa foi cancelada."),
  PAUSED("A execução da tarefa está interrompida temporariamente."),
  UNDER_REVIEW("A tarefa está sob revisão.");

  private final String description;


}
