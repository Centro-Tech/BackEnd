package school.sptech.projetoMima.SemCleanArch.exception.Item;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemFormatoInvalido extends RuntimeException {
  public ItemFormatoInvalido(String message) {
    super(message);
  }
}
