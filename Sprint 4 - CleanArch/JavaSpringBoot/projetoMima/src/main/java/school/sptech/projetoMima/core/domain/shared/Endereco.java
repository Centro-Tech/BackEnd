package school.sptech.projetoMima.core.domain.shared;

public class Endereco {
    private final String valor;

    public Endereco(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("Endereço inválido");
        }
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}

