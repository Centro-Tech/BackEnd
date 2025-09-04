package school.sptech.projetoMima.core.application.usecase.Item;

import school.sptech.projetoMima.core.adapter.Item.ItemGateway;
import school.sptech.projetoMima.core.domain.item.Item;

public class DeletarItemUseCase {

    private final ItemGateway itemGateway;

    public DeletarItemUseCase(ItemGateway itemGateway) {
        this.itemGateway = itemGateway;
    }

    public void execute(Item item) {
        itemGateway.delete(item);
    }
}
