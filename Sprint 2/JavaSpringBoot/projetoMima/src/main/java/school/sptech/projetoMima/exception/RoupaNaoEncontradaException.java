package school.sptech.projetoMima.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoupaNaoEncontradaException extends RuntimeException {
    public RoupaNaoEncontradaException(String message) {
        super(message);
    }
}
