package school.sptech.projetoMima.service.auxiliares;

import org.springframework.stereotype.Service;
import school.sptech.projetoMima.entity.item.Material;
import school.sptech.projetoMima.repository.auxiliares.MaterialRepository;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository repository;

    public MaterialService(MaterialRepository repository) {
        this.repository = repository;
    }

    public List<Material> listar() {
        return repository.findAll();
    }

    public Material buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Material não encontrada"));
    }

    public Material salvar(Material material) {
        return repository.save(material);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
