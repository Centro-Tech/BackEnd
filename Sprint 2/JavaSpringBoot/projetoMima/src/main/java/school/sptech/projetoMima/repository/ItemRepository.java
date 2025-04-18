package school.sptech.projetoMima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.entity.Item;

public interface ItemRepository extends JpaRepository <Item, Integer> {
    boolean existsByCodigo(String codigo);
}
