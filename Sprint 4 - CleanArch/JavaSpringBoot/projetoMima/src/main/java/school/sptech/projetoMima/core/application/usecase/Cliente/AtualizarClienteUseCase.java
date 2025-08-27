package school.sptech.projetoMima.core.application.usecase.Cliente;

import school.sptech.projetoMima.core.adapter.Cliente.ClienteMutation;
import school.sptech.projetoMima.core.domain.Cliente;

public class AtualizarClienteUseCase {

    private final ClienteMutation clienteMutation;

    public AtualizarClienteUseCase(ClienteMutation clienteMutation) {
        this.clienteMutation = clienteMutation;
    }

    Cliente execute(Integer id, Cliente clienteAtualizado) {
        if (!clienteMutation.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        clienteAtualizado.setId(id);
        return clienteMutation.save(clienteAtualizado);
    }
}
