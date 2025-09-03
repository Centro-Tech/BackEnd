package school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.auxiliares.Material;

import school.sptech.projetoMima.core.adapter.Item.auxiliares.MaterialGateway;
import school.sptech.projetoMima.core.domain.item.Material;

import java.util.List;

public class MaterialJpaAdapter implements MaterialGateway {

    private final MaterialRepository materialRepository;

    public MaterialJpaAdapter(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Material save(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public boolean existsByNomeIgnoreCase(String nome) {
        return materialRepository.existsByNomeIgnoreCase(nome);
    }

    @Override
    public boolean existsById(Integer id) {
        return materialRepository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        materialRepository.deleteById(id);
    }

    @Override
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    @Override
    public Material findById(Integer id) {
        return materialRepository.findById(id).orElse(null);
    }
}
