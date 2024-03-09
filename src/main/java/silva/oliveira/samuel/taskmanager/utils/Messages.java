package silva.oliveira.samuel.taskmanager.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {

  private static final String BASENAME = "messages";

  private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(BASENAME, Locale.getDefault());

  public static String get(String key, Object... params) {
    try {
      var message = resourceBundle.getString(key);
      return MessageFormat.format(message, params);
    } catch (Exception e) {
      return key;
    }
  }

  public static String getOrNull(String key, Object... params) {
    try {
      var message = resourceBundle.getString(key);
      return MessageFormat.format(message, params);
    } catch (Exception e) {
      return null;
    }
  }

}