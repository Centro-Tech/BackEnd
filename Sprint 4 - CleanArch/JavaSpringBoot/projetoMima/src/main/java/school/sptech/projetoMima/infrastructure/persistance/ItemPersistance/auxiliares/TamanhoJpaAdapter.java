package school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.auxiliares;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.TamanhoGateway;
import school.sptech.projetoMima.core.domain.item.Tamanho;
import school.sptech.projetoMima.infrastructure.bd.Item.auxiliares.Tamanho.TamanhoRepository;

import java.util.List;

public class TamanhoJpaAdapter implements TamanhoGateway {

    private final TamanhoRepository tamanhoRepository;

    public TamanhoJpaAdapter(TamanhoRepository tamanhoRepository) {
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
