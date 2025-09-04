package school.sptech.projetoMima.core.application.usecase.Item.auxiliares.TamanhoUseCase;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.TamanhoGateway;
import school.sptech.projetoMima.core.application.command.Fornecedor.CadastrarFornecedorCommand;
import school.sptech.projetoMima.core.application.exception.Item.Auxiliares.TamanhoDuplicadoException;
import school.sptech.projetoMima.core.domain.item.Tamanho;

public class CriarTamanhoUseCase {

    private final TamanhoGateway gateway;

    public CriarTamanhoUseCase(TamanhoGateway gateway) {
        this.gateway = gateway;
    }

    public Tamanho execute (CadastrarFornecedorCommand command) {

        if (gateway.existsByNomeIgnoreCase(command.nome())) {
            throw new TamanhoDuplicadoException("Já existe um tamanho com esse nome");
        }

        Tamanho tamanho = new Tamanho();
        tamanho.setNome(command.nome());

        gateway.save(tamanho);
        return tamanho;
    }
}
