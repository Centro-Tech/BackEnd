package school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.auxiliares;

import org.springframework.stereotype.Component;
import school.sptech.projetoMima.core.adapter.Item.ItemGateway;
import school.sptech.projetoMima.core.domain.item.Item;
import school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.ItemRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ItemJpaAdapter implements ItemGateway {

    private final ItemRepository repository;

    public ItemJpaAdapter(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public boolean existsByCodigo(String codigo) {
        return repository.existsByCodigo(codigo);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByCodigo(String codigo) {
        repository.deleteByCodigo(codigo);
    }

    @Override
    public List<Item> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Item> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Item> findByCodigo(String codigo) {
        return repository.findByCodigo(codigo);
    }

    @Override
    public List<Item> findByCategoriaNomeContainsIgnoreCase(String nomeCategoria) {
        return repository.findByCategoriaNomeContainsIgnoreCase(nomeCategoria);
    }

    @Override
    public List<Item> findByFornecedorNomeContainsIgnoreCase(String nome) {
        return repository.findByFornecedorNomeContainsIgnoreCase(nome);
    }

    @Override
    public List<Item> findByNomeContainsIgnoreCase(String nome) {
        return repository.findByNomeContainsIgnoreCase(nome);
    }
}
