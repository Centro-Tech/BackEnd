package school.sptech.projetoMima.SemCleanArch.exception.Venda;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.NOT_ACCEPTABLE)
public class CarrinhoVazioException extends RuntimeException {
    public CarrinhoVazioException(String message) {
        super(message);
    }
}
