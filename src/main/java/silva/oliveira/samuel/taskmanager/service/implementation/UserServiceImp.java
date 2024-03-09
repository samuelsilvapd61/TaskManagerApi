package silva.oliveira.samuel.taskmanager.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import silva.oliveira.samuel.taskmanager.domain.exception.ApiErrorEnum;
import silva.oliveira.samuel.taskmanager.domain.exception.ApiException;
import silva.oliveira.samuel.taskmanager.domain.request.UserRequest;
import silva.oliveira.samuel.taskmanager.domain.request.UserUpdateRequest;
import silva.oliveira.samuel.taskmanager.domain.response.UserResponse;
import silva.oliveira.samuel.taskmanager.repository.UserRepository;
import silva.oliveira.samuel.taskmanager.service.UserService;

/**
 * Classe referente à tarefa que aplica regras de negócio e faz comunicação com o repositório.
 */
@Service
public class UserServiceImp implements UserService {

  @Autowired
  private UserRepository userRepository;


  @Override
  public UserResponse getUserById(Long id) {
    var user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new ApiException(ApiErrorEnum.USER_DOESNT_EXIST);
    }
    return UserResponse.builder().id(user.get().getId()).build();
  }

  @Override
  public void addUser(UserRequest request) {

  }

  @Override
  public void updateUser(Long id, UserUpdateRequest request) {

  }

  @Override
  public void deleteUser(Long id) {

  }
}