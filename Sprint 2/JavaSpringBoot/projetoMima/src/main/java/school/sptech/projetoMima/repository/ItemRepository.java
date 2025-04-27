package school.sptech.projetoMima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.entity.item.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository <Item, Integer> {
    boolean existsByCodigo(String codigo);


    List<Item> findByCategoriaNomeContainsIgnoreCase(String categoria);

    List<Item> findByFornecedorNomeContainsIgnoreCase(String nome);

    List<Item> findByNomeContainsIgnoreCase(String nome);
}
