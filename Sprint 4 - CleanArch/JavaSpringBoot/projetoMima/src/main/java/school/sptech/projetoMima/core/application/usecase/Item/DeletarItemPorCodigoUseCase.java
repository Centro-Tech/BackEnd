package school.sptech.projetoMima.core.application.usecase.Item;

import school.sptech.projetoMima.core.adapter.Item.ItemGateway;
import school.sptech.projetoMima.core.application.command.Item.DeletarItemPorCodigoCommand;
import school.sptech.projetoMima.core.application.exception.Item.ItemNaoEncontradoException;

public class DeletarItemPorCodigoUseCase {

    private final ItemGateway itemGateway;

    public DeletarItemPorCodigoUseCase(ItemGateway itemGateway) {
        this.itemGateway = itemGateway;
    }

    public void execute(DeletarItemPorCodigoCommand command) {
        if (!itemGateway.existsByCodigo(command.codigo())) {
            throw new ItemNaoEncontradoException("Item com código " + command.codigo() + " não encontrado");
        }
        itemGateway.deleteByCodigo(command.codigo());
    }
}
