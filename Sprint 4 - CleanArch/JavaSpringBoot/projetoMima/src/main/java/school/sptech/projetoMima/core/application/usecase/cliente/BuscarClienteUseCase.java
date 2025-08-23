package school.sptech.projetoMima.core.application.usecase.cliente;
import school.sptech.projetoMima.core.adapter.cliente.ClienteRead;
import school.sptech.projetoMima.core.domain.Cliente;
import school.sptech.projetoMima.SemCleanArch.exception.Cliente.ClienteNaoEncontradoException;

public class BuscarClienteUseCase {

    private final ClienteRead read;

    public BuscarClienteUseCase(ClienteRead read) {
        this.read = read;
    }

    public Cliente executar(Integer id) {
        return read.buscarPorId(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente com o ID " + id + " não encontrado!"));
    }
}
