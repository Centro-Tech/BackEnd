package school.sptech.projetoMima.core.application.usecase.ItemVenda;

import school.sptech.projetoMima.core.adapter.ItemVenda.ItemVendaGateway;
import school.sptech.projetoMima.core.adapter.Venda.VendaGateway;
import school.sptech.projetoMima.core.application.command.ItemVenda.FinalizarCarrinhoCommand;
import school.sptech.projetoMima.core.application.exception.Venda.CarrinhoVazioException;
import school.sptech.projetoMima.core.domain.ItemVenda;
import school.sptech.projetoMima.core.domain.Venda;

import java.util.List;

public class FinalizarCarrinhoUseCase {

    private final ItemVendaGateway itemVendaGateway;
    private final VendaGateway vendaGateway;

    public FinalizarCarrinhoUseCase(ItemVendaGateway itemVendaGateway, VendaGateway vendaGateway) {
        this.itemVendaGateway = itemVendaGateway;
        this.vendaGateway = vendaGateway;
    }

    public void execute(FinalizarCarrinhoCommand command) {
        List<ItemVenda> carrinho = itemVendaGateway.findByClienteIdAndVendaIsNull(command.clienteId());

        if (carrinho.isEmpty()) {
            throw new CarrinhoVazioException("Carrinho está vazio.");
        }

        Venda venda = vendaGateway.findById(command.vendaId());
        if (venda == null) {
            throw new RuntimeException("Venda não encontrada");
        }

        for (ItemVenda item : carrinho) {
            item.setVenda(venda);
            itemVendaGateway.save(item);
        }
    }
}
