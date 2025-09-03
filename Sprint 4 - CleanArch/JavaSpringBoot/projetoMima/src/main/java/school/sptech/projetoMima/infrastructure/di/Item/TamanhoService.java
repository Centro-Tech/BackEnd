package school.sptech.projetoMima.core.application.usecase.auxiliares;

import org.springframework.stereotype.Service;
import school.sptech.projetoMima.core.application.exception.Item.Auxiliares.TamanhoDuplicadoException;
import school.sptech.projetoMima.core.application.exception.Item.Auxiliares.TamanhoListaVaziaException;
import school.sptech.projetoMima.core.application.exception.Item.Auxiliares.TamanhoNaoEncontradoException;
import school.sptech.projetoMima.core.domain.item.Tamanho;
import school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.auxiliares.Tamanho.TamanhoRepository;

import java.util.List;

@Service
public class TamanhoService {
    private final TamanhoRepository repository;

    public TamanhoService(TamanhoRepository repository) {
        this.repository = repository;
    }

    public Tamanho salvar(Tamanho tamanho) {
        if (repository.existsByNomeIgnoreCase(tamanho.getNome())) {
            throw new TamanhoDuplicadoException("Tamanho já cadastrado com esse nome.");
        }
        return repository.save(tamanho);
    }

    public Tamanho buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new TamanhoNaoEncontradoException("Tamanho não encontrado"));
    }

    public void deletar(Integer id) {
        if(!repository.existsById(id)) {
            throw new TamanhoNaoEncontradoException("esse tamanho não existe na lista!");
        }

        repository.deleteById(id);
    }

    public List<Tamanho> listar() {
        if (repository.findAll().isEmpty()) {
            throw new TamanhoListaVaziaException("não há tamanhos cadastrados");
        }
        return repository.findAll();
    }
}
