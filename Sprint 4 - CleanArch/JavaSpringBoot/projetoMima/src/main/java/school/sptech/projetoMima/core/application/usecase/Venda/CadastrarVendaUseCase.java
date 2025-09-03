package school.sptech.projetoMima.core.application.usecase.Venda;

import school.sptech.projetoMima.core.adapter.Cliente.ClienteGateway;
import school.sptech.projetoMima.core.adapter.Usuario.UsuarioGateway;
import school.sptech.projetoMima.core.adapter.Venda.VendaGateway;
import school.sptech.projetoMima.core.application.command.Venda.CreateVendaCommand;
import school.sptech.projetoMima.core.application.exception.Usuario.UsuarioNaoEncontradoException;
import school.sptech.projetoMima.core.domain.Cliente;
import school.sptech.projetoMima.core.domain.Usuario;
import school.sptech.projetoMima.core.domain.Venda;

public class CadastrarVendaUseCase {

    private final VendaGateway vendaGateway;

    private final ClienteGateway clienteGateway;

    private final UsuarioGateway usuarioGateway;

    public CadastrarVendaUseCase(VendaGateway vendaGateway, ClienteGateway clienteRepository, ClienteGateway clienteGateway, UsuarioGateway usuarioGateway) {
        this.vendaGateway = vendaGateway;
        this.clienteGateway = clienteGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public Venda executar(CreateVendaCommand command) {
       // Usuario usuario = vendaGateway.findById(command.getUsuarioId())
        //        .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

     //   Cliente cliente = vendaGateway.findById(venda.getClienteId())
       //.orElseThrow(() -> new UsuarioNaoEncontradoException("Cliente não encontrado"));

        return null;
    }
}
