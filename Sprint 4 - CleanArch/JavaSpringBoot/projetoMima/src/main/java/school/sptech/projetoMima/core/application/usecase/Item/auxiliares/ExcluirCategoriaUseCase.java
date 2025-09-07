package school.sptech.projetoMima.core.application.usecase.Item.auxiliares;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.CategoriaGateway;
import school.sptech.projetoMima.core.application.command.Item.auxiliares.CategoriaCommand.DeletarCategoriaCommand;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.projetoMima.core.application.exception.Item.Auxiliares.CategoriaNaoEncontradaException;

public class ExcluirCategoriaUseCase {

    private final CategoriaGateway categoriaGateway;

    public ExcluirCategoriaUseCase(CategoriaGateway categoriaGateway) {
        this.categoriaGateway = categoriaGateway;
    }

    @Transactional
    public void execute(DeletarCategoriaCommand command) {
        if (!categoriaGateway.existsById(command.id())) {
            throw new CategoriaNaoEncontradaException("Categoria com id " + command.id() + " não encontrada");
        }
        categoriaGateway.deleteById(command.id());
    }
}
