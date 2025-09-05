package school.sptech.projetoMima.core.application.usecase.Item.auxiliares.TamanhoUseCase;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.TamanhoGateway;
import school.sptech.projetoMima.core.application.exception.Item.Auxiliares.TamanhoNaoEncontradoException;

public class DeletarTamanhoUseCase {

    private final TamanhoGateway gateway;

    public DeletarTamanhoUseCase(TamanhoGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(Integer id) {
        if (!gateway.existsById(id)) {
            throw new TamanhoNaoEncontradoException("Tamanho não encontrado");
        }
        gateway.deleteById(id);
    }
}