package school.sptech.projetoMima.controller.auxiliares;

import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.entity.item.Material;
import school.sptech.projetoMima.service.auxiliares.MaterialService;

import java.util.List;

@RestController
@RequestMapping("/materiais")
public class MaterialController {

    private final MaterialService service;

    public MaterialController(MaterialService service) {
        this.service = service;
    }

    @GetMapping
    public List<Material> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Material buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Material criar(@RequestBody Material material) {
        return service.salvar(material);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}