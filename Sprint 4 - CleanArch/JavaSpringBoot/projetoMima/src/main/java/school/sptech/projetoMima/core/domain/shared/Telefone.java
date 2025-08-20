package school.sptech.projetoMima.core.domain.shared;

import java.util.regex.Pattern;

public class Telefone {
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^\\+?\\d{10,15}$");
    private final String numero;

    public Telefone(String numero) {
        if (numero == null || !numero.matches(TELEFONE_PATTERN.pattern())) {
            throw new IllegalArgumentException("Telefone inválido.");
        }
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return numero;
    }
}
