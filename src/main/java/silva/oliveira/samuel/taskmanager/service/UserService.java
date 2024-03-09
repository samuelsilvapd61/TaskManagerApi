package silva.oliveira.samuel.taskmanager.service;

import org.springframework.data.domain.Pageable;
import silva.oliveira.samuel.taskmanager.domain.request.TaskRequest;
import silva.oliveira.samuel.taskmanager.domain.request.TaskUpdateRequest;
import silva.oliveira.samuel.taskmanager.domain.request.UserRequest;
import silva.oliveira.samuel.taskmanager.domain.request.UserUpdateRequest;
import silva.oliveira.samuel.taskmanager.domain.response.TaskResponse;
import silva.oliveira.samuel.taskmanager.domain.response.UserResponse;

import java.util.List;

public interface UserService {

  UserResponse getUserById(Long id);
  void addUser(UserRequest request);
  void updateUser(Long id, UserUpdateRequest request);
  void deleteUser(Long id);

}