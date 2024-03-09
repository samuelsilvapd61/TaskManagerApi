package silva.oliveira.samuel.taskmanager.domain.exception;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import silva.oliveira.samuel.taskmanager.utils.Messages;

import java.util.*;

import static java.time.LocalDateTime.now;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  public static final String SUFFIX_ENUM_ERROR = ".enum-error";
  //public static final String HEADER_NOT_FOUND_ENUM_ERROR = "msg.header.not-null.enum-error";

  record StringLength(String value, int length) {}

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<Object> handleApiException(ApiException ex, WebRequest request) {
    var apiError = buildApiError(ex);
    var status = HttpStatus.valueOf(apiError.getStatus());
    return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ObjectError error = fetchObjectError(ex);
    String[] codes = fetchErrorCodes(error);
    String messageKey = fetchExistingKeyMessageOrDefault(codes);
    var apiError = Optional.ofNullable(messageKey)
        .map(key -> key.concat(SUFFIX_ENUM_ERROR))
        .map(Messages::get)
        .map(ApiErrorEnum::valueOfOrNull)
        .map(ApiExceptionHandler::buildApiError)
        .orElseGet(() -> buildApiError(ApiErrorEnum.INVALID_FIELD));
    return ResponseEntity.badRequest().body(apiError);
  }
//  public ResponseEntity<ApiErrorDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//    ObjectError error = fetchObjectError(ex);
//    String[] codes = fetchErrorCodes(error);
//    String messageKey = fetchExistingKeyMessageOrDefault(codes);
//    var apiError = Optional.ofNullable(messageKey)
//        .map(key -> key.concat(SUFFIX_ENUM_ERROR))
//        .map(Messages::get)
//        .map(ApiErrorEnum::valueOfOrNull)
//        .map(ApiExceptionHandler::buildApiError)
//        .orElseGet(() -> buildApiError(ApiErrorEnum.INVALID_FIELD));
//    return ResponseEntity.badRequest().body(apiError);
//  }

  private ObjectError fetchObjectError(MethodArgumentNotValidException ex) {
    List<ObjectError> allErrors = new ArrayList<>(ex.getBindingResult().getAllErrors());
    return allErrors.stream()
        .filter(a -> Objects.nonNull(a.getDefaultMessage()))
        .min(Comparator.comparing(DefaultMessageSourceResolvable::getDefaultMessage))
        .orElseThrow();
  }

  private String[] fetchErrorCodes(ObjectError error) {
    if (Objects.isNull(error.getCodes())) {
      return new String[]{};
    }
    var className = error.getObjectName();
    return Arrays.stream(error.getCodes())
        .filter(code -> code.contains(className))
        .map(code -> buildMessageCodes(className, code))
        .flatMap(Collection::stream)
        .toArray(String[]::new);
  }



  private String fetchExistingKeyMessageOrDefault(String[] codes) {
    String keyMessage = findExistingKeyMessage(codes);
    return Optional.ofNullable(keyMessage)
        .orElseGet(() -> ArrayUtils.isNotEmpty(codes) ? codes[0] : null);
  }

  private String findExistingKeyMessage(String[] errorCodes) {
    List<StringLength> codes = Arrays.stream(errorCodes)
        .map(strLen -> new StringLength(strLen, strLen.length()))
        .sorted(Comparator.comparingInt(StringLength::length).reversed())
        .toList();

    for (var code : codes) {
      var message = Messages.getOrNull(code.value);
      if (Objects.nonNull(message)) {
        return code.value;
      }
    }
    return  null;
  }

  private List<String> buildMessageCodes(String className, String code) {
    return List.of(code, className);
  }

  public static ApiErrorDto buildApiError(ApiException ex) {
    return buildApiError(ex.getMessage(), ex.getErrorCode(), ex.getTitle(), ex.getHttpStatus());
  }

  public static ApiErrorDto buildApiError(ApiErrorEnum errorEnum) {
    return buildApiError(errorEnum.getFormattedMessage(), errorEnum.getCode(), errorEnum.getTitle(), errorEnum.getStatus());
  }

  public static ApiErrorDto buildApiError(String message, String errorCode, String title, HttpStatus httpStatus) {
    return ApiErrorDto.builder()
        .title(title)
        .detail(message)
        .code(errorCode)
        .status(httpStatus.value())
        .timestamp(now())
        .build();
  }


}
