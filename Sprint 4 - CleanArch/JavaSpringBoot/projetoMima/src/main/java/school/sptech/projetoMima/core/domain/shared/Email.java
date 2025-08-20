package school.sptech.projetoMima.core.domain.shared;

import java.util.regex.Pattern;

public class Email {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private final String endereco;

    public Email(String endereco) {
        if (endereco == null || !endereco.matches(EMAIL_PATTERN.pattern())) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return endereco;
    }
}
