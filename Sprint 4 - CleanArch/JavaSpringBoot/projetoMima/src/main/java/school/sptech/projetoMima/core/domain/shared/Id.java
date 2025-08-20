package school.sptech.projetoMima.core.domain.shared;

import java.util.UUID;

public class Id {
    private final UUID valor;

    public Id(UUID valor) {
        if (valor == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        this.valor = valor;
    }

    public static Id gerarNovo() {
        return new Id(UUID.randomUUID());
    }

    public UUID getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor.toString();
    }
}
