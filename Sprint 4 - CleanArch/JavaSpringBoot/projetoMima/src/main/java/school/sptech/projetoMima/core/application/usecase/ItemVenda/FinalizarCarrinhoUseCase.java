package school.sptech.projetoMima.core.application.usecase.ItemVenda;

import school.sptech.projetoMima.core.adapter.ItemVenda.ItemVendaGateway;
import school.sptech.projetoMima.core.application.command.ItemVenda.FinalizarCarrinhoCommand;
import school.sptech.projetoMima.core.application.exception.Venda.CarrinhoVazioException;
import school.sptech.projetoMima.core.application.usecase.Venda.CriarVendaUseCase;
import school.sptech.projetoMima.core.application.command.Venda.CriarVendaCommand;
import school.sptech.projetoMima.core.domain.ItemVenda;
import school.sptech.projetoMima.core.domain.Venda;

import java.util.List;
import java.util.stream.Collectors;

public class FinalizarCarrinhoUseCase {

    private final ItemVendaGateway itemVendaGateway;
    private final CriarVendaUseCase criarVendaUseCase;

    public FinalizarCarrinhoUseCase(ItemVendaGateway itemVendaGateway, CriarVendaUseCase criarVendaUseCase) {
        this.itemVendaGateway = itemVendaGateway;
        this.criarVendaUseCase = criarVendaUseCase;
    }

    public Venda execute(FinalizarCarrinhoCommand command) {
        // Busca TODOS os itens do carrinho (sem venda)
        List<ItemVenda> carrinho = itemVendaGateway.findByVendaIsNull();

        if (carrinho.isEmpty()) {
            throw new CarrinhoVazioException("Carrinho está vazio.");
        }

        // Calcula o valor total da venda somando os itens
        double valorTotal = carrinho.stream()
                .mapToDouble(item -> item.getItem().getPreco() * item.getQtdParaVender())
                .sum();

        // Monta o comando para criar a venda
        // NOTA: funcionarioId está hardcoded como 1 por enquanto
        CriarVendaCommand criarCommand = new CriarVendaCommand(
                valorTotal,
                command.clienteId(),
                carrinho.stream()
                        .map(item -> item.getItem().getId())
                        .collect(Collectors.toList()),
                1 // Funcionário fixo por enquanto
        );

        // Cria a venda
        Venda novaVenda = criarVendaUseCase.execute(criarCommand);

        // Associa todos os itens do carrinho à nova venda
        for (ItemVenda item : carrinho) {
            item.setVenda(novaVenda);
            itemVendaGateway.save(item);
        }

        return novaVenda;
    }
}
