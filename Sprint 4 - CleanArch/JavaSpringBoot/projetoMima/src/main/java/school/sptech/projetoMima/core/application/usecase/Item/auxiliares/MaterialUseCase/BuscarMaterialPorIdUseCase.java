package school.sptech.projetoMima.core.application.usecase.Item.auxiliares.MaterialUseCase;

import school.sptech.projetoMima.core.domain.item.Material;
import school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.auxiliares.Material.MaterialRepository;

public class BuscarMaterialPorIdUseCase {

    private final MaterialRepository repository;

    public BuscarMaterialPorIdUseCase(MaterialRepository repository) {
        this.repository = repository;
    }
    public Material buscarMaterialPorId(Long id) {

    }
}
