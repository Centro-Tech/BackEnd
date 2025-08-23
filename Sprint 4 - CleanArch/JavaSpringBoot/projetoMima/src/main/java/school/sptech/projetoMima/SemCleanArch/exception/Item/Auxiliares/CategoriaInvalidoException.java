package school.sptech.projetoMima.SemCleanArch.exception.Item.Auxiliares;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoriaInvalidoException extends RuntimeException {
    public CategoriaInvalidoException(String message) {
        super(message);
    }
}
