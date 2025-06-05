package school.sptech.projetoMima.controller.auxiliares;

import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.entity.item.Cor;
import school.sptech.projetoMima.service.auxiliares.CorService;

import java.util.List;

@RestController
@RequestMapping("/cores")
public class CorController {

    private final CorService service;

    public CorController(CorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cor> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Cor buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Cor criar(@RequestBody Cor cor) {
        return service.salvar(cor);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}
