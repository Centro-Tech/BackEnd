package school.sptech.projetoMima.core.application.usecase.Item;

import school.sptech.projetoMima.core.application.exception.Item.ItemNaoEncontradoException;
import school.sptech.projetoMima.core.adapter.Item.ItemGateway;
import school.sptech.projetoMima.core.domain.item.Item;

import java.util.List;

public class FiltrarItemPorNomeUseCase {

    private final ItemGateway itemGateway;

    public FiltrarItemPorNomeUseCase(ItemGateway itemGateway) {
        this.itemGateway = itemGateway;
    }

    public List<Item> execute(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum termo digitado!");
        }

        List<Item> itens = itemGateway.findByNomeContainsIgnoreCase(nome);

        if (itens == null || itens.isEmpty()) {
            throw new ItemNaoEncontradoException("Itens não encontrados no estoque!");
        }

        return itens;
    }
}
