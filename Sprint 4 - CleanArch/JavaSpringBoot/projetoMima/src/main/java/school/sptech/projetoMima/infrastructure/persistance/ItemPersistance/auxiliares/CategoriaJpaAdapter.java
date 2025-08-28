package school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.auxiliares;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.CategoriaGateway;
import school.sptech.projetoMima.core.domain.item.Categoria;
import school.sptech.projetoMima.infrastructure.bd.Item.auxiliares.Categoria.CategoriaRepository;

import java.util.List;

public class CategoriaJpaAdapter implements CategoriaGateway {

    private final CategoriaRepository categoriaRepository;

    public CategoriaJpaAdapter(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public boolean existsByNomeIgnoreCase(String nome) {
        return categoriaRepository.existsByNomeIgnoreCase(nome);
    }

    @Override
    public boolean existsById(Integer id) {
        return categoriaRepository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }
}
