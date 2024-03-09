package silva.oliveira.samuel.taskmanager.domain.exception;

import io.micrometer.common.util.StringUtils;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Getter
public class ApiException extends RuntimeException {

  public static final String PREFIX_MSG_KEY = "msg.";

  protected HttpStatus httpStatus;
  protected String errorCode;
  protected String title;

  public ApiException(ApiErrorEnum errorEnum) {
    super(errorEnum.getFormattedMessage());
    fillFields(errorEnum.getStatus(), errorEnum.getCode(), errorEnum.getTitle());
  }

  protected void fillFields(HttpStatus status, String code, String title) {
    this.httpStatus = Optional.ofNullable(status)
        .orElseThrow(() -> new NullPointerException("status não pode ser null"));
    this.errorCode = Optional.ofNullable(code)
        .orElseThrow(() -> new NullPointerException("code não pode ser null"));
    this.title = StringUtils.isBlank(title) ? this.httpStatus.getReasonPhrase() : title.trim();
  }

}
