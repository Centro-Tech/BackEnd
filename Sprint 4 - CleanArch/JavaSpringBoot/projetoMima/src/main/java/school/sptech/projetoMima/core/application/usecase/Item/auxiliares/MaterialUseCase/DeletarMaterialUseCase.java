package school.sptech.projetoMima.core.application.usecase.Item.auxiliares.MaterialUseCase;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.MaterialGateway;

public class DeletarMaterialUseCase {
    private final MaterialGateway gateway;

    public DeletarMaterialUseCase(MaterialGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }

        if (!gateway.existsById(id)) {
            throw new school.sptech.projetoMima.core.application.exception.Item.auxiliares.MaterialNaoEncontradoException("Material não encontrado");
        }

        gateway.deleteById(id);
    }
}
