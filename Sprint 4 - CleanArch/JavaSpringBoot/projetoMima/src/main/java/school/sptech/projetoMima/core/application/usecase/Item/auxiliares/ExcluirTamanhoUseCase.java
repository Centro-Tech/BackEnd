package school.sptech.projetoMima.core.application.usecase.Item.auxiliares;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.TamanhoGateway;
import school.sptech.projetoMima.core.application.command.Item.auxiliares.TamanhoCommad.DeletarTamanhoCommand;
import school.sptech.projetoMima.core.application.exception.Item.auxiliares.TamanhoNaoEncontradoException;

public class ExcluirTamanhoUseCase {

    private final TamanhoGateway tamanhoGateway;

    public ExcluirTamanhoUseCase(TamanhoGateway tamanhoGateway) {
        this.tamanhoGateway = tamanhoGateway;
    }

    public void execute(DeletarTamanhoCommand command) {
        if (!tamanhoGateway.existsById(command.id())) {
            throw new TamanhoNaoEncontradoException("Tamanho com id " + command.id() + " não encontrado");
        }
        tamanhoGateway.deleteById(command.id());
    }
}
