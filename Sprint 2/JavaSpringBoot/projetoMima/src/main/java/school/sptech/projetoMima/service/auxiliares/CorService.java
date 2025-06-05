package school.sptech.projetoMima.service.auxiliares;

import org.springframework.stereotype.Service;
import school.sptech.projetoMima.entity.item.Cor;
import school.sptech.projetoMima.repository.auxiliares.CorRepository;

import java.util.List;

@Service
public class CorService {

    private final CorRepository repository;

    public CorService(CorRepository repository) {
        this.repository = repository;
    }

    public List<Cor> listar() {
        return repository.findAll();
    }

    public Cor buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cor não encontrada"));
    }

    public Cor salvar(Cor cor) {
        return repository.save(cor);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
