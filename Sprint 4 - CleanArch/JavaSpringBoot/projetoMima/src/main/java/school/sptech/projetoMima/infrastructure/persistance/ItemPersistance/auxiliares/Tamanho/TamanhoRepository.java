package school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.auxiliares.Tamanho;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.core.domain.item.Tamanho;

import java.util.List;
import java.util.Optional;

public interface TamanhoRepository extends JpaRepository<Tamanho, Integer> {
    boolean existsByNomeIgnoreCase(String nome);

    Optional<Tamanho> findById(Integer id);

    boolean existsById(Integer id);

    Tamanho save(Tamanho tamanho);

    void deleteById(Integer id);

    List<Tamanho> findAll();

}
