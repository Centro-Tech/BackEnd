package school.sptech.projetoMima.core.application.command.Item;

import school.sptech.projetoMima.core.domain.item.Item;

public record ExcluirItemCommand(
        Item item
) { }
