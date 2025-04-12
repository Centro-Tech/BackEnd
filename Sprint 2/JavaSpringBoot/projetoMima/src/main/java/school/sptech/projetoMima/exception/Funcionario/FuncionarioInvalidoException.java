package school.sptech.projetoMima.exception.Funcionario;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FuncionarioInvalidoException extends RuntimeException {
    public FuncionarioInvalidoException(String message) {
        super(message);
    }
}
