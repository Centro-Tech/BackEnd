package school.sptech.projetoMima.core.application.usecase.Item;

import school.sptech.projetoMima.core.application.exception.Item.ItemNaoEncontradoException;
import school.sptech.projetoMima.core.adapter.Item.ItemGateway;
import school.sptech.projetoMima.core.domain.item.Item;

import java.util.Optional;

public class DeletarItemPorCodigoUseCase {

    private final ItemGateway itemGateway;

    public DeletarItemPorCodigoUseCase(ItemGateway itemGateway) {
        this.itemGateway = itemGateway;
    }

    public void execute(String codigo) {
        Optional<Item> item;
        try {
            item = itemGateway.findByCodigo(codigo);
        } catch (Exception e) {
            throw new ItemNaoEncontradoException("Item com código '" + codigo + "' não encontrado");
        }

        itemGateway.delete(item.orElseThrow(() -> new ItemNaoEncontradoException("Item com código '" + codigo + "' não encontrado")));
    }
}