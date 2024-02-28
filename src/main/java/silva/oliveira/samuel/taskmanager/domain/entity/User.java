package silva.oliveira.samuel.taskmanager.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que representa o usuário.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity(name = "user")
public class User {

  @Id
  private Long id;
  private String email;
  private String password;

}
