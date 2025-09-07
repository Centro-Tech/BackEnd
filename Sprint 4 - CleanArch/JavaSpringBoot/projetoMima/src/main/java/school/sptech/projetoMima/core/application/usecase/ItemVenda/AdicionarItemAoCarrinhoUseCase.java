package school.sptech.projetoMima.core.application.usecase.ItemVenda;

import school.sptech.projetoMima.core.adapter.ItemVenda.ItemVendaGateway;
import school.sptech.projetoMima.core.adapter.Cliente.ClienteGateway;
import school.sptech.projetoMima.core.adapter.Usuario.UsuarioGateway;
import school.sptech.projetoMima.core.adapter.Item.ItemGateway;
import school.sptech.projetoMima.core.application.command.ItemVenda.AdicionarItemAoCarrinhoCommand;
import school.sptech.projetoMima.core.application.exception.ItemVenda.ItemNaoEncontradoException;
import school.sptech.projetoMima.core.application.exception.ItemVenda.ClienteNaoEncontradoException;
import school.sptech.projetoMima.core.application.exception.ItemVenda.FuncionarioNaoEncontradoException;
import school.sptech.projetoMima.core.domain.ItemVenda;
import school.sptech.projetoMima.core.domain.Cliente;
import school.sptech.projetoMima.core.domain.Usuario;
import school.sptech.projetoMima.core.domain.item.Item;

public class AdicionarItemAoCarrinhoUseCase {

    private final ItemVendaGateway itemVendaGateway;
    private final ItemGateway itemGateway;
    private final ClienteGateway clienteGateway;
    private final UsuarioGateway usuarioGateway;

    public AdicionarItemAoCarrinhoUseCase(ItemVendaGateway itemVendaGateway,
                                         ItemGateway itemGateway,
                                         ClienteGateway clienteGateway,
                                         UsuarioGateway usuarioGateway) {
        this.itemVendaGateway = itemVendaGateway;
        this.itemGateway = itemGateway;
        this.clienteGateway = clienteGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public ItemVenda execute(AdicionarItemAoCarrinhoCommand command) {
        Item item = itemGateway.findById(command.itemId())
                .orElseThrow(() -> new ItemNaoEncontradoException("Item não encontrado"));

        Cliente cliente = clienteGateway.findById(command.clienteId());
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado");
        }

        Usuario funcionario = usuarioGateway.findById(command.funcionarioId())
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário não encontrado"));

        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setItem(item);
        itemVenda.setCliente(cliente);
        itemVenda.setFuncionario(funcionario);
        itemVenda.setFornecedor(item.getFornecedor());
        itemVenda.setQtdParaVender(command.qtdParaVender());
        itemVenda.setVenda(null);

        return itemVendaGateway.save(itemVenda);
    }
}
