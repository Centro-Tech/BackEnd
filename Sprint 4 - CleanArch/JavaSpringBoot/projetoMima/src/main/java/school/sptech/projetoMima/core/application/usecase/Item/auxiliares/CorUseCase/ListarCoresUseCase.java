package school.sptech.projetoMima.core.application.usecase.Item.auxiliares.CorUseCase;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.CorGateway;
import school.sptech.projetoMima.core.domain.item.Cor;

import java.util.List;

public class ListarCoresUseCase {
    private final CorGateway gateway;
    public ListarCoresUseCase(CorGateway gateway) { this.gateway = gateway; }

    public List<Cor> execute() {
        List<Cor> cores = gateway.findAll();
        if(cores.isEmpty()) throw new RuntimeException("Nenhuma cor cadastrada.");
        return cores;
    }
}

