package school.sptech.projetoMima.controller.auxiliares;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.entity.item.Tamanho;
import school.sptech.projetoMima.service.auxiliares.TamanhoService;

@RestController
@RequestMapping("/tamanhos")
public class TamanhoController {

    private final TamanhoService service;

    public TamanhoController(TamanhoService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar novo tamanho")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Tamanho cadastrado com sucesso") })
    @PostMapping
    public Tamanho criar(@RequestBody Tamanho tamanho) {
        return service.salvar(tamanho);
    }

    @Operation(summary = "Excluir tamanho por ID")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Tamanho excluído com sucesso"), @ApiResponse(responseCode = "404", description = "Tamanho não encontrado para exclusão") })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        Tamanho tamanho = service.buscarPorId(id);
        service.deletar(id);
    }
}
