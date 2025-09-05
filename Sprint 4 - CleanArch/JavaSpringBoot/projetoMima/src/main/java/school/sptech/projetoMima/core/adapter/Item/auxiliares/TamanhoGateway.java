package school.sptech.projetoMima.core.adapter.Item.auxiliares;

import school.sptech.projetoMima.core.domain.item.Tamanho;
import java.util.List;

public interface TamanhoGateway {
    Tamanho save(Tamanho tamanho);

    boolean existsByNomeIgnoreCase(String nome);

    boolean existsById(Integer id);

    void deleteById(Integer id);

    List<Tamanho> findAll();

    Tamanho findById(Integer id);
}
