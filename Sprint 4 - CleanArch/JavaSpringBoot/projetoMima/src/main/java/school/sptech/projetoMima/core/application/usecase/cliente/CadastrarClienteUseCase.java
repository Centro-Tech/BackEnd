package school.sptech.projetoMima.core.application.usecase.cliente;

import school.sptech.projetoMima.core.adapter.cliente.ClienteMutation;
import school.sptech.projetoMima.core.adapter.cliente.command.CadastrarClienteCommand;
import school.sptech.projetoMima.core.domain.Cliente;
import school.sptech.projetoMima.core.domain.shared.Endereco;

public class CadastrarClienteUseCase {

    private final ClienteMutation mutation;

    public CadastrarClienteUseCase(ClienteMutation mutation) {
        this.mutation = mutation;
    }

    public Cliente executar(CadastrarClienteCommand cmd) {
        Cliente cliente = new Cliente(
                null,
                cmd.nome(),
                cmd.cpf(),
                cmd.telefone(),
                cmd.email(),
                new Endereco(cmd.endereco())
        );
        return mutation.salvar(cliente);
    }

}
