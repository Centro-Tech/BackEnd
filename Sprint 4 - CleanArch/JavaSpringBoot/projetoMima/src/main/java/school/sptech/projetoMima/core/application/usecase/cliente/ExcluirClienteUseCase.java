package school.sptech.projetoMima.core.application.usecase.cliente;

import school.sptech.projetoMima.core.adapter.cliente.ClienteMutation;
import school.sptech.projetoMima.core.adapter.cliente.ClienteRead;
import school.sptech.projetoMima.SemCleanArch.exception.Cliente.ClienteNaoEncontradoException;

public class ExcluirClienteUseCase {

    private final ClienteRead read;
    private final ClienteMutation mutation;

    public ExcluirClienteUseCase(ClienteRead read, ClienteMutation mutation) {
        this.read = read;
        this.mutation = mutation;
    }

    public void executar(Integer id) {
        read.buscarPorId(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));
        mutation.deletarPorId(id);
    }
}