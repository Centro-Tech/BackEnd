package school.sptech.projetoMima.core.application.usecase.Cliente;

import school.sptech.projetoMima.core.adapter.Cliente.ClienteMutation;
import school.sptech.projetoMima.core.application.command.Cliente.ExcluirClienteCommand;
import school.sptech.projetoMima.core.application.exception.Cliente.ClienteNaoEncontradoException;

public class ExcluirClienteUseCase {

    private final ClienteMutation clienteMutation;

    public ExcluirClienteUseCase(ClienteMutation clienteMutation) {
        this.clienteMutation = clienteMutation;
    }

    public void execute(ExcluirClienteCommand id) {
        if (!clienteMutation.existsById(id.id())) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado");
        }
        clienteMutation.deleteById(id.id());
    }
}
