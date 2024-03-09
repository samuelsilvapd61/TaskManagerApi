package silva.oliveira.samuel.taskmanager.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import silva.oliveira.samuel.taskmanager.utils.Messages;

@Getter
public enum ApiErrorEnum {

  USER_DOESNT_EXIST ("U-4040", "user-doesnt-exist", HttpStatus.NOT_FOUND, "Bad Request"),
  TASK_NAME_NOT_EMPTY ("T-4000", "NotEmpty.taskRequest.name", HttpStatus.BAD_REQUEST, "Bad Request"),
  TASK_DESCRIPTION_NOT_EMPTY ("T-4001", "NotEmpty.taskRequest.description", HttpStatus.BAD_REQUEST, "Bad Request"),
  TASK_OWNER_ID_NOT_NULL ("T-4002", "NotNull.taskRequest.taskOwnerId", HttpStatus.BAD_REQUEST, "Bad Request"),
  INVALID_FIELD     ("VE-4000", "msg.invalid.field", HttpStatus.BAD_REQUEST, "Bad Request");

  private final String code;
  private final String messageKey;
  private final HttpStatus status;
  private final String title;
  private Object[] messageParams;

  ApiErrorEnum(String code, String messageKey, HttpStatus status) {
    this(code, messageKey, status, null);
  }

  ApiErrorEnum(String code, String messageKey, HttpStatus status, String title) {
    this.code = code;
    this.messageKey = messageKey;
    this.status = status;
    this.title = title;
  }

  public String getFormattedMessage() {
    return Messages.get(this.messageKey, this.messageParams);
  }

  public static ApiErrorEnum valueOfOrNull(String name) {
    try {
      return ApiErrorEnum.valueOf(name);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

}
