package school.sptech.projetoMima.core.application.usecase.Item;

import school.sptech.projetoMima.core.application.exception.Item.ItemNaoEncontradoException;
import school.sptech.projetoMima.core.adapter.Item.ItemGateway;
import school.sptech.projetoMima.core.domain.item.Item;

import java.util.Optional;

public class BuscarItemPorIdUseCase {

    private final ItemGateway itemGateway;

    public BuscarItemPorIdUseCase(ItemGateway itemGateway) {
        this.itemGateway = itemGateway;
    }

    public Optional<Item> execute(int id) {
        try {
            return itemGateway.findById(id);
        } catch (Exception e) {
            throw new ItemNaoEncontradoException("Item com o id " + id + " não encontrado.");
        }
    }
}
