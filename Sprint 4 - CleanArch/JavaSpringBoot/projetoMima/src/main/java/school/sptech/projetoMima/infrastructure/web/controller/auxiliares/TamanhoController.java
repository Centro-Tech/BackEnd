package school.sptech.projetoMima.infrastructure.web.controller.auxiliares;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.core.application.command.Item.auxiliares.TamanhoCommad.CriarTamanhoCommand;
import school.sptech.projetoMima.core.application.dto.fornecedorDto.FornecedorMapper;
import school.sptech.projetoMima.core.application.dto.fornecedorDto.FornecedorResponseDto;
import school.sptech.projetoMima.core.application.usecase.Item.auxiliares.TamanhoUseCase.BuscarTamanhoPorIdUseCase;
import school.sptech.projetoMima.core.application.usecase.Item.auxiliares.TamanhoUseCase.CriarTamanhoUseCase;
import school.sptech.projetoMima.core.application.usecase.Item.auxiliares.TamanhoUseCase.DeletarTamanhoUseCase;
import school.sptech.projetoMima.core.application.usecase.Item.auxiliares.TamanhoUseCase.ListarTamanhosUseCase;
import school.sptech.projetoMima.core.domain.Fornecedor;
import school.sptech.projetoMima.core.domain.item.Tamanho;
import school.sptech.projetoMima.infrastructure.persistance.FornecedorPersistance.FornecedorEntityMapper;
import school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.auxiliares.Tamanho.Entity.TamanhoEntity;
import school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.auxiliares.Tamanho.Entity.TamanhoEntityMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tamanhos")
public class TamanhoController {

    private final CriarTamanhoUseCase criarTamanhoUseCase;
    private final ListarTamanhosUseCase listarTamanhosUseCase;
    private final DeletarTamanhoUseCase deletarTamanhoUseCase;
    private final BuscarTamanhoPorIdUseCase buscarTamanhoPorIdUseCase;

    public TamanhoController(CriarTamanhoUseCase criarTamanhoUseCase, ListarTamanhosUseCase listarTamanhosUseCase, DeletarTamanhoUseCase deletarTamanhoUseCase, BuscarTamanhoPorIdUseCase buscarTamanhoPorIdUseCase) {
        this.criarTamanhoUseCase = criarTamanhoUseCase;
        this.listarTamanhosUseCase = listarTamanhosUseCase;
        this.deletarTamanhoUseCase = deletarTamanhoUseCase;
        this.buscarTamanhoPorIdUseCase = buscarTamanhoPorIdUseCase;
    }

    @Operation(summary = "Listar todos os tamanhos")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Tamanhos encontrados com sucesso") })
    @GetMapping
    public ResponseEntity<List<TamanhoEntity>> listar() {
        List<Tamanho> tamanhosEncontrados = listarTamanhosUseCase.execute();
        if (tamanhosEncontrados.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        List<TamanhoEntity> response = tamanhosEncontrados.stream().map(TamanhoEntityMapper::toEntity).toList();
        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "Cadastrar novo tamanho")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Tamanho cadastrado com sucesso"), @ApiResponse(responseCode = "400", description = "Dados inválidos para cadastro"), @ApiResponse(responseCode = "409", description = "Tamanho já existente")})
    @PostMapping
    public ResponseEntity<TamanhoEntity> criar(@RequestBody Tamanho tamanho) {
        CriarTamanhoCommand command = new CriarTamanhoCommand(tamanho.getNome());

        Tamanho tamanhoCriado = criarTamanhoUseCase.execute(command);

        return ResponseEntity.status(201).body(TamanhoEntityMapper.toEntity(tamanhoCriado));
    }

    @Operation(summary = "Excluir tamanho por ID")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Tamanho excluído com sucesso"), @ApiResponse(responseCode = "404", description = "Tamanho não encontrado para exclusão") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        deletarTamanhoUseCase.execute(id);
        return ResponseEntity.status(204).build();
    }

    @Operation(summary = "Buscar tamanho por ID")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Tamanho encontrado com sucesso"), @ApiResponse(responseCode = "404", description = "Tamanho não encontrado com o ID informado") })
    @GetMapping("/{id}")
    public ResponseEntity<TamanhoEntity> buscar(@PathVariable int id) {
        Optional<Tamanho> tamanho = Optional.ofNullable(buscarTamanhoPorIdUseCase.execute(id));
        if (tamanho.isPresent()) {
            Tamanho tamanhoNovo = tamanho.get();
            return ResponseEntity.ok(TamanhoEntityMapper.toEntity(tamanhoNovo));
        }
        return ResponseEntity.notFound().build();
    }
}