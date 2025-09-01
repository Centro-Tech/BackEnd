package school.sptech.projetoMima.core.application.usecase.Fornecedor;

import school.sptech.projetoMima.core.adapter.Fornecedor.FornecedorGateway;
import school.sptech.projetoMima.core.application.command.Cliente.ExcluirClienteCommand;
import school.sptech.projetoMima.core.application.command.Fornecedor.ExcluirFornecedorCommand;
import school.sptech.projetoMima.core.application.exception.Cliente.ClienteNaoEncontradoException;
import school.sptech.projetoMima.core.application.exception.Fornecedor.FornecedorNaoEncontradoException;

public class DeletarFornecedorUseCase {

    private final FornecedorGateway gateway;

    public DeletarFornecedorUseCase(FornecedorGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(ExcluirFornecedorCommand id) {
        if (!gateway.existsById(id.id())) {
            throw new FornecedorNaoEncontradoException("Fornecedor não encontrado");
        }
        gateway.deleteById(id.id());
    }
}
