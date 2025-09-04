package school.sptech.projetoMima.core.application.usecase.Item.auxiliares.CorUseCase;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.CorGateway;

public class DeletarCorUseCase {
    private final CorGateway gateway;
    public DeletarCorUseCase(CorGateway gateway) { this.gateway = gateway; }

    public void execute(Integer id) { gateway.deleteById(id); }
}
