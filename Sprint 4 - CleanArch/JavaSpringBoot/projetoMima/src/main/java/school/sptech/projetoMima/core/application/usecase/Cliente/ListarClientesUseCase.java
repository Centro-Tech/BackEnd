package school.sptech.projetoMima.core.application.usecase.Cliente;

import school.sptech.projetoMima.core.adapter.Cliente.ClienteGateway;
import school.sptech.projetoMima.core.application.exception.Cliente.ClienteListaVaziaException;
import school.sptech.projetoMima.core.domain.Cliente;

import java.util.List;

public class ListarClientesUseCase {

    private final ClienteGateway clienteGateway;

    public ListarClientesUseCase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public List<Cliente> execute() {
        List<Cliente> clientes = clienteGateway.findAll();
        if (clientes.isEmpty()) {
            throw new ClienteListaVaziaException("Lista de clientes está vazia");
        }
        return clientes;
    }
}
