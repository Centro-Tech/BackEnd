package school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.auxiliares.Cor;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.CorGateway;
import school.sptech.projetoMima.core.domain.item.Cor;

import java.util.List;

public class CorJpaRepository implements CorGateway {

    private final CorRepository corRepository;

    public CorJpaRepository(CorRepository corRepository) {
        this.corRepository = corRepository;
    }

    @Override
    public Cor save(Cor cor) {
        return corRepository.save(cor);
    }

    @Override
    public boolean existsByNomeIgnoreCase(String nome) {
        return corRepository.existsByNomeIgnoreCase(nome);
    }

    @Override
    public boolean existsById(Integer id) {
        return corRepository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        corRepository.deleteById(id);
    }

    @Override
    public List<Cor> findAll() {
        return corRepository.findAll();
    }

    @Override
    public Cor findById(Integer id) {
        return corRepository.findById(id).orElse(null);
    }
}
