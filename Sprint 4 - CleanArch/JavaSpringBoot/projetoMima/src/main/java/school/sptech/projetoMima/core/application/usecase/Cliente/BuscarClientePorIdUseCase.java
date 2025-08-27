package school.sptech.projetoMima.core.application.usecase.Cliente;

import school.sptech.projetoMima.core.adapter.Cliente.ClienteMutation;
import school.sptech.projetoMima.core.application.exception.Cliente.ClienteNaoEncontradoException;
import school.sptech.projetoMima.core.domain.Cliente;

public class BuscarClientePorIdUseCase {

    private final ClienteMutation clienteMutation;

    public BuscarClientePorIdUseCase(ClienteMutation clienteMutation) {
        this.clienteMutation = clienteMutation;
    }

    public Cliente execute(Integer id) {
        if(!clienteMutation.existsById(id)) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado");
        }
        return clienteMutation.findById(id).get();
    }
}
