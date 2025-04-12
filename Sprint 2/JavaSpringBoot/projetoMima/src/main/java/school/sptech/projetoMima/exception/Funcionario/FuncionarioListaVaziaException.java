package school.sptech.projetoMima.exception.Funcionario;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class FuncionarioListaVaziaException extends RuntimeException {
    public FuncionarioListaVaziaException(String message) {
        super(message);
    }
}
