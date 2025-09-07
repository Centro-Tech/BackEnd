package school.sptech.projetoMima.core.application.usecase.ItemVenda;

import school.sptech.projetoMima.core.adapter.ItemVenda.ItemVendaGateway;
import school.sptech.projetoMima.core.application.command.ItemVenda.ListarCarrinhoCommand;
import school.sptech.projetoMima.core.application.exception.Venda.CarrinhoVazioException;
import school.sptech.projetoMima.core.domain.ItemVenda;

import java.util.List;

public class ListarCarrinhoUseCase {

    private final ItemVendaGateway itemVendaGateway;

    public ListarCarrinhoUseCase(ItemVendaGateway itemVendaGateway) {
        this.itemVendaGateway = itemVendaGateway;
    }

    public List<ItemVenda> execute(ListarCarrinhoCommand command) {

        List<ItemVenda> carrinho = itemVendaGateway.findByClienteIdAndVendaIsNull(command.clienteId());

        if (carrinho.isEmpty()) {
            throw new CarrinhoVazioException("O carrinho está vazio.");
        }

        return carrinho;
    }
}
