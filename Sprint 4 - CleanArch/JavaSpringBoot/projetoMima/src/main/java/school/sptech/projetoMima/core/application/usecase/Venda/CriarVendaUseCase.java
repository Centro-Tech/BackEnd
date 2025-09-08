package school.sptech.projetoMima.core.application.usecase.Venda;

import school.sptech.projetoMima.core.adapter.Venda.VendaGateway;
import school.sptech.projetoMima.core.adapter.Cliente.ClienteGateway;
import school.sptech.projetoMima.core.adapter.Item.ItemGateway;
import school.sptech.projetoMima.core.adapter.Usuario.UsuarioGateway;
import school.sptech.projetoMima.core.application.command.Venda.CriarVendaCommand;
import school.sptech.projetoMima.core.application.exception.Venda.CarrinhoVazioException;
import school.sptech.projetoMima.core.domain.Venda;
import school.sptech.projetoMima.core.domain.Cliente;
import school.sptech.projetoMima.core.domain.ItemVenda;
import school.sptech.projetoMima.core.domain.item.Item;
import school.sptech.projetoMima.core.domain.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CriarVendaUseCase {

    private final VendaGateway vendaGateway;
    private final ClienteGateway clienteGateway;
    private final ItemGateway itemGateway;
    private final UsuarioGateway usuarioGateway;

    public CriarVendaUseCase(VendaGateway vendaGateway, ClienteGateway clienteGateway, ItemGateway itemGateway, UsuarioGateway usuarioGateway) {
        this.vendaGateway = vendaGateway;
        this.clienteGateway = clienteGateway;
        this.itemGateway = itemGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public Venda execute(CriarVendaCommand command) {

        if (command.itensVenda() == null || command.itensVenda().isEmpty()) {
            throw new CarrinhoVazioException("O carrinho não pode estar vazio");
        }

        Cliente cliente = clienteGateway.findById(command.clienteId());
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        Usuario funcionario = usuarioGateway.findById(command.funcionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        List<ItemVenda> itensVenda = new ArrayList<>();
        for (Integer itemId : command.itensVenda()) {
            Item item = itemGateway.findById(itemId);
            if (item == null) {
                throw new RuntimeException("Item não encontrado: " + itemId);
            }

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setItem(item);
            itemVenda.setCliente(cliente);
            itemVenda.setFuncionario(funcionario);
            itemVenda.setQtdParaVender(1); // quantidade padrão
            itemVenda.setFornecedor(item.getFornecedor());
            itensVenda.add(itemVenda);
        }

        Venda venda = new Venda();
        venda.setValorTotal(command.valorTotal());
        venda.setCliente(cliente);
        venda.setUsuario(funcionario);
        venda.setItensVenda(itensVenda);

        return vendaGateway.save(venda);
    }
}
