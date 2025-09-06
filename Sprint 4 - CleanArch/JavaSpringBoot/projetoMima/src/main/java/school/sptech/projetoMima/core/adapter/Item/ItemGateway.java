package school.sptech.projetoMima.core.adapter.Item;

import school.sptech.projetoMima.core.domain.item.Item;

import java.util.List;

public interface ItemGateway {
    Item save(Item item);

    boolean existsByCodigo(String codigo);

    boolean existsById(Integer id);

    void deleteById(Integer id);

    void deleteByCodigo(String codigo);

    List<Item> findAll();

    Item findById(Integer id);

    Item findByCodigo(String codigo);

    List<Item> findByCategoriaNomeContainsIgnoreCase(String nomeCategoria);

    List<Item> findByFornecedorNomeContainsIgnoreCase(String nome);

    List<Item> findByNomeContainsIgnoreCase(String nome);

    List<Item> findByCodigoContainsIgnoreCase(String codigo);
}
