package silva.oliveira.samuel.taskmanager.domain.request;

import jakarta.validation.constraints.NotEmpty;

/**
 * Classe de request do usuário.
 */
public class UserRequest {

  @NotEmpty
  private String email;
  @NotEmpty
  private String password;

}
