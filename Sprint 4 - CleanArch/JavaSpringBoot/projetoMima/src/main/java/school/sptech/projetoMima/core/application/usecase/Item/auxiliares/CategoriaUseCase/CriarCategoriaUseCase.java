package school.sptech.projetoMima.core.application.usecase.Item.auxiliares.CategoriaUseCase;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.CategoriaGateway;
import school.sptech.projetoMima.core.domain.item.Categoria;

public class CriarCategoriaUseCase {
    private final CategoriaGateway gateway;

    public CriarCategoriaUseCase(CategoriaGateway gateway) {
        this.gateway = gateway;
    }

    public Categoria execute(Categoria categoria) {
        if(categoria == null || categoria.getNome() == null || categoria.getNome().isBlank()) {
            throw new IllegalArgumentException("Categoria inválida.");
        }
        if(gateway.existsByNomeIgnoreCase(categoria.getNome())) {
            throw new IllegalArgumentException("Categoria já cadastrada.");
        }
        return gateway.save(categoria);
    }
}

