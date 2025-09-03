package school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.auxiliares.Tamanho;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.TamanhoGateway;
import school.sptech.projetoMima.core.domain.item.Tamanho;

import java.util.List;

public class TamanhoJpaRepository implements TamanhoGateway {

    private final TamanhoRepository tamanhoRepository;

    public TamanhoJpaRepository(TamanhoRepository tamanhoRepository) {
        this.tamanhoRepository = tamanhoRepository;
    }

    @Override
    public Tamanho save(Tamanho tamanho) {
        return tamanhoRepository.save(tamanho);
    }

    @Override
    public boolean existsByNomeIgnoreCase(String nome) {
        return tamanhoRepository.existsByNomeIgnoreCase(nome);
    }

    @Override
    public boolean existsById(Integer id) {
        return tamanhoRepository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        tamanhoRepository.deleteById(id);
    }

    @Override
    public List<Tamanho> findAll() {
        return tamanhoRepository.findAll();
    }

    @Override
    public Tamanho findById(Integer id) {
        return tamanhoRepository.findById(id).orElse(null);
    }
}
