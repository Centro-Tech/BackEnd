package school.sptech.projetoMima.core.application.usecase.Item.auxiliares.CategoriaUseCase;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.CategoriaGateway;
import school.sptech.projetoMima.core.application.exception.Item.Auxiliares.CategoriaNaoEncontradoException;

public class DeletarCategoriaUseCase {
    private final CategoriaGateway gateway;

    public DeletarCategoriaUseCase(CategoriaGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(Integer id) {
        if(!gateway.existsById(id)) throw new CategoriaNaoEncontradoException("Categoria não encontrada para exclusão.");
        gateway.deleteById(id);
    }
}

