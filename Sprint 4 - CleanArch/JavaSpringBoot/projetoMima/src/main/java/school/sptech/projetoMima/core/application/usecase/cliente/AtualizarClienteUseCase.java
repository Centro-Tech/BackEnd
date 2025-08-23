package school.sptech.projetoMima.core.application.usecase.cliente;

import school.sptech.projetoMima.core.adapter.cliente.ClienteMutation;
import school.sptech.projetoMima.core.adapter.cliente.ClienteRead;
import school.sptech.projetoMima.core.adapter.cliente.command.AtualizarClienteCommand;
import school.sptech.projetoMima.core.domain.Cliente;
import school.sptech.projetoMima.SemCleanArch.exception.Cliente.ClienteNaoEncontradoException;
import school.sptech.projetoMima.core.domain.shared.Endereco;

public class AtualizarClienteUseCase {

    private final ClienteRead read;
    private final ClienteMutation mutation;

    public AtualizarClienteUseCase(ClienteRead read, ClienteMutation mutation) {
        this.read = read;
        this.mutation = mutation;
    }

    public Cliente executar(AtualizarClienteCommand cmd) {
        Cliente existente = read.buscarPorId(cmd.id())
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente com o ID " + cmd.id() + " não encontrado!"));

        Cliente atualizado = existente.atualizarDados(
                cmd.nome(),
                cmd.cpf(),
                cmd.telefone(),
                cmd.email(),
                new Endereco(cmd.endereco())
        );

        return mutation.salvar(atualizado);
    }
}