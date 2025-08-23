package school.sptech.projetoMima.core.domain.shared;

public class Email {
    private final String endereco;

    public Email() {
        this.endereco = "teste@teste.com";
    }

    public Email(String endereco) {
        if (endereco == null || !endereco.matches(".+@.+\\..+")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        this.endereco = endereco;
    }

    public String getValor() {
        return endereco;
    }

    @Override
    public String toString() {
        return endereco;
    }
}


