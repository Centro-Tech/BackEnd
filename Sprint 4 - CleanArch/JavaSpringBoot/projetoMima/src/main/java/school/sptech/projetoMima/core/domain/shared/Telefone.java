package school.sptech.projetoMima.core.domain.shared;

public class Telefone {
    private final String numero;

    public Telefone() {
        this.numero = "0000000000";
    }

    public Telefone(String numero) {
        if (numero == null || !numero.matches("\\d{10,15}")) {
            throw new IllegalArgumentException("Telefone inválido.");
        }
        this.numero = numero;
    }

    public String getValor() {
        return numero;
    }

    @Override
    public String toString() {
        return numero;
    }
}

