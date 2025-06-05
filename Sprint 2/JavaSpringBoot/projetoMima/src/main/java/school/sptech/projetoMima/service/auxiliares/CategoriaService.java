package school.sptech.projetoMima.service.auxiliares;

import org.springframework.stereotype.Service;
import school.sptech.projetoMima.entity.item.Categoria;
import school.sptech.projetoMima.repository.auxiliares.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Categoria buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public Categoria salvar(Categoria categoria) {
        return repository.save(categoria);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
