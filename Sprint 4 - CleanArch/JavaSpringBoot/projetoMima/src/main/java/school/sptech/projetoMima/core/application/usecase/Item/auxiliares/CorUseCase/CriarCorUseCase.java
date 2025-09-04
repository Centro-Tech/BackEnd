package school.sptech.projetoMima.core.application.usecase.Item.auxiliares.CorUseCase;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.CorGateway;
import school.sptech.projetoMima.core.domain.item.Cor;

public class CriarCorUseCase {
    private final CorGateway gateway;
    public CriarCorUseCase(CorGateway gateway) { this.gateway = gateway; }

    public Cor execute(Cor cor) {
        if(cor == null || cor.getNome() == null || cor.getNome().isBlank()) throw new IllegalArgumentException("Cor inválida.");
        if(gateway.existsByNomeIgnoreCase(cor.getNome())) throw new IllegalArgumentException("Cor já cadastrada.");
        return gateway.save(cor);
    }
}
