package school.sptech.projetoMima.service.auxiliares;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import school.sptech.projetoMima.entity.item.Tamanho;
import school.sptech.projetoMima.exception.Item.Auxiliares.TamanhoDuplicadoException;
import school.sptech.projetoMima.repository.auxiliares.TamanhoRepository;

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
                .orElseThrow(() -> new RuntimeException("Tamanho não encontrado"));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public List<Tamanho> listar() {
        return repository.findAll();
    }
}
