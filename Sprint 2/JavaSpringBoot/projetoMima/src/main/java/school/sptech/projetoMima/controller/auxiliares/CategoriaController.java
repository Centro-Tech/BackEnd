package school.sptech.projetoMima.controller.auxiliares;

import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.entity.item.Categoria;
import school.sptech.projetoMima.service.auxiliares.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Categoria> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Categoria buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Categoria criar(@RequestBody Categoria categoria) {
        return service.salvar(categoria);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}
