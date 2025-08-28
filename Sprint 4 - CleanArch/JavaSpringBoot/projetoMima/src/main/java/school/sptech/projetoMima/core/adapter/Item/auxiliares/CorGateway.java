package school.sptech.projetoMima.core.adapter.Item.auxiliares;

import school.sptech.projetoMima.core.domain.item.Cor;
import java.util.List;

public interface CorGateway {
    Cor save(Cor cor);
    boolean existsByNomeIgnoreCase(String nome);
    boolean existsById(Integer id);
    void deleteById(Integer id);
    List<Cor> findAll();
    Cor findById(Integer id);
}
