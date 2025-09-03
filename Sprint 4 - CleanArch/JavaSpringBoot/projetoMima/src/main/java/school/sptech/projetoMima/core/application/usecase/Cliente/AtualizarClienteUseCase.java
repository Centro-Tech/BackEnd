package school.sptech.projetoMima.core.application.usecase.Cliente;

import school.sptech.projetoMima.core.adapter.Cliente.ClienteGateway;
import school.sptech.projetoMima.core.application.command.Cliente.AtualizarClienteCommand;
import school.sptech.projetoMima.core.domain.Cliente;

public class AtualizarClienteUseCase {

    private final ClienteGateway clienteGateway;

    public AtualizarClienteUseCase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public Cliente execute(AtualizarClienteCommand command) {
        Integer id = command.id();

        if (!clienteGateway.existsById(id.intValue())) {
            throw new RuntimeException("Cliente não encontrado");
        }

        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setId(id.intValue());
        clienteAtualizado.setNome(command.nome());
        clienteAtualizado.setEmail(command.email());
        clienteAtualizado.setCPF(command.cpf());
        clienteAtualizado.setTelefone(command.telefone());

        return clienteGateway.save(clienteAtualizado);
    }
}
