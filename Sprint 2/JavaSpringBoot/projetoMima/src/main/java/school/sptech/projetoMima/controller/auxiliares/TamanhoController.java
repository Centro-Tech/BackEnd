package school.sptech.projetoMima.controller.auxiliares;

import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.entity.item.Tamanho;
import school.sptech.projetoMima.service.auxiliares.TamanhoService;

import java.util.List;

@RestController
@RequestMapping("/tamanhos")
public class TamanhoController {
    private final TamanhoService service;

    public TamanhoController(TamanhoService service) {
        this.service = service;
    }

    @PostMapping
    public Tamanho criar(@RequestBody Tamanho tamanho) {
        return service.salvar(tamanho);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        Tamanho tamanho = service.buscarPorId(id);
        service.deletar(id);
    }
}