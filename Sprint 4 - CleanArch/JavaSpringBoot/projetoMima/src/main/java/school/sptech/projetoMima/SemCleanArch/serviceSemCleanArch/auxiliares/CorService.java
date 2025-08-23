package school.sptech.projetoMima.SemCleanArch.serviceSemCleanArch.auxiliares;

import org.springframework.stereotype.Service;
import school.sptech.projetoMima.core.domain.item.Cor;
import school.sptech.projetoMima.SemCleanArch.exception.Item.Auxiliares.CorDuplicadaException;
import school.sptech.projetoMima.SemCleanArch.exception.Item.Auxiliares.CorListaVaziaException;
import school.sptech.projetoMima.SemCleanArch.exception.Item.Auxiliares.CorNaoEncontradoException;
import school.sptech.projetoMima.SemCleanArch.repositorySemCleanArch.auxiliares.CorRepository;

import java.util.List;

@Service
public class CorService {

    private final CorRepository repository;

    public CorService(CorRepository repository) {
        this.repository = repository;
    }

    public List<Cor> listar() {
        List<Cor> cores = repository.findAll();
        if (cores.isEmpty()) {
            throw new CorListaVaziaException("Nenhuma cor cadastrada.");
        }
        return cores;
    }

    public Cor buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new CorNaoEncontradoException("Cor com ID " + id + " não encontrada"));
    }

    public Cor salvar(Cor cor) {
        if (repository.existsByNomeIgnoreCase(cor.getNome())) {
            throw new CorDuplicadaException("Cor com nome '" + cor.getNome() + "' já cadastrada.");
        }
        return repository.save(cor);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
