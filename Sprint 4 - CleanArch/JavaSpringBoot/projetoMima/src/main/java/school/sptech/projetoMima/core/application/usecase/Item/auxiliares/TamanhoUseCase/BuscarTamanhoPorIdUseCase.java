package school.sptech.projetoMima.core.application.usecase.Item.auxiliares.TamanhoUseCase;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.TamanhoGateway;
import school.sptech.projetoMima.core.application.exception.Item.Auxiliares.TamanhoInvalidoException;
import school.sptech.projetoMima.core.domain.item.Tamanho;

public class BuscarTamanhoPorIdUseCase {

    private final TamanhoGateway gateway;

    public BuscarTamanhoPorIdUseCase(TamanhoGateway gateway) {
        this.gateway = gateway;
    }

    public Tamanho execute (Integer id) {

        if (!gateway.existsById(id)) {
            throw new TamanhoInvalidoException("Tamanho não encontrado");
        }
        return gateway.findById(id);
    }
}
