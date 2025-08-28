package school.sptech.projetoMima.core.application.usecase.Cliente;

import school.sptech.projetoMima.core.adapter.Cliente.ClienteGateway;
import school.sptech.projetoMima.core.domain.Cliente;

public class AtualizarClienteUseCase {

    private final ClienteGateway clienteGateway;

    public AtualizarClienteUseCase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    Cliente execute(Integer id, Cliente clienteAtualizado) {
        if (!clienteGateway.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        clienteAtualizado.setId(id);
        return clienteGateway.save(clienteAtualizado);
    }
}
