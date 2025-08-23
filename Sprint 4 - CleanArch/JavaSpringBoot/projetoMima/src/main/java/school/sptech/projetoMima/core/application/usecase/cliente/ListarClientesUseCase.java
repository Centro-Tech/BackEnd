package school.sptech.projetoMima.core.application.usecase.cliente;

import school.sptech.projetoMima.core.adapter.cliente.ClienteRead;
import school.sptech.projetoMima.core.domain.Cliente;
import school.sptech.projetoMima.SemCleanArch.exception.Cliente.ClienteListaVaziaException;

import java.util.List;

public class ListarClientesUseCase {

    private final ClienteRead read;

    public ListarClientesUseCase(ClienteRead read) {
        this.read = read;
    }

    public List<Cliente> executar() {
        List<Cliente> clientes = read.buscarTodos();
        if (clientes.isEmpty()) {
            throw new ClienteListaVaziaException("Lista de clientes está vazia");
        }
        return clientes;
    }
}