package school.sptech.projetoMima.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.dto.funcionarioDto.FuncionarioCadastroDto;
import school.sptech.projetoMima.dto.funcionarioDto.FuncionarioResumidoDto;
import school.sptech.projetoMima.dto.funcionarioDto.FuncionarioMapper;
import school.sptech.projetoMima.entity.Funcionario;
import school.sptech.projetoMima.service.FuncionarioService;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Operation(summary = "Buscar funcionário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado", content = @Content(schema = @Schema(implementation = FuncionarioResumidoDto.class))),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResumidoDto> buscarPorId(@PathVariable Integer id) {
        Funcionario funcionario = funcionarioService.findFuncionarioById(id);
        FuncionarioResumidoDto dto = FuncionarioMapper.toResumidoDto(funcionario);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Listar todos os funcionários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionários listados com sucesso", content = @Content(schema = @Schema(implementation = FuncionarioResumidoDto.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum funcionário encontrado")
    })
    @GetMapping
    public ResponseEntity<List<FuncionarioResumidoDto>> listar() {
        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
        List<FuncionarioResumidoDto> response = funcionarios.stream()
                .map(FuncionarioMapper::toResumidoDto)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Inserir novo funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso", content = @Content(schema = @Schema(implementation = FuncionarioCadastroDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<FuncionarioResumidoDto> cadastrar(@RequestBody FuncionarioCadastroDto dto) {
        Funcionario novoFuncionario = funcionarioService.cadastrarFuncionario(dto);
        return ResponseEntity.status(201).body(FuncionarioMapper.toResumidoDto(novoFuncionario));
    }

    @Operation(summary = "Atualizar funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso", content = @Content(schema = @Schema(implementation = FuncionarioCadastroDto.class))),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResumidoDto> atualizar(@RequestBody FuncionarioCadastroDto dto, @PathVariable Integer id) {
        Funcionario funcionarioAtualizado = funcionarioService.atualizarFuncionario(dto, id);
        FuncionarioResumidoDto dtoResposta = FuncionarioMapper.toResumidoDto(funcionarioAtualizado);
        return ResponseEntity.ok(dtoResposta);
    }

    @Operation(summary = "Deletar funcionário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        funcionarioService.excluir(id);
        return ResponseEntity.status(204).build();
    }
}
