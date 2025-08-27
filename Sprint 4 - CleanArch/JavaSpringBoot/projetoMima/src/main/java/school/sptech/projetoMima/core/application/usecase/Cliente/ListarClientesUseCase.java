package school.sptech.projetoMima.core.application.usecase.Cliente;

import school.sptech.projetoMima.core.adapter.Cliente.ClienteMutation;
import school.sptech.projetoMima.core.application.exception.Cliente.ClienteListaVaziaException;
import school.sptech.projetoMima.core.domain.Cliente;

import java.util.List;

public class ListarClientesUseCase {

    private final ClienteMutation clienteMutation;

    public ListarClientesUseCase(ClienteMutation clienteMutation) {
        this.clienteMutation = clienteMutation;
    }

    public List<Cliente> execute() {
        List<Cliente> clientes = clienteMutation.findAll();
        if (clientes.isEmpty()) {
            throw new ClienteListaVaziaException("Lista de clientes está vazia");
        }
        return clientes;
    }
}
