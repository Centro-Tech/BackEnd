package school.sptech.projetoMima.core.application.exception.Item.Auxiliares;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TamanhoNaoEncontradoException extends RuntimeException {
  public TamanhoNaoEncontradoException(String message) {
    super(message);
  }
}
