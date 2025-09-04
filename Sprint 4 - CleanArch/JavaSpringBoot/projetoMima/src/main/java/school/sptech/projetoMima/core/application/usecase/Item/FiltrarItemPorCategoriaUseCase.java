package school.sptech.projetoMima.core.application.usecase.Item;

import school.sptech.projetoMima.core.application.exception.Item.ItemNaoEncontradoException;
import school.sptech.projetoMima.core.adapter.Item.ItemGateway;
import school.sptech.projetoMima.core.domain.item.Item;

import java.util.List;

public class FiltrarItemPorCategoriaUseCase {

    private final ItemGateway itemGateway;

    public FiltrarItemPorCategoriaUseCase(ItemGateway itemGateway) {
        this.itemGateway = itemGateway;
    }

    public List<Item> execute(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum termo digitado!");
        }

        List<Item> itens = itemGateway.findByCategoriaNomeContainsIgnoreCase(categoria);

        if (itens == null || itens.isEmpty()) {
            throw new ItemNaoEncontradoException("Itens não encontrados no estoque!");
        }

        return itens;
    }
}
