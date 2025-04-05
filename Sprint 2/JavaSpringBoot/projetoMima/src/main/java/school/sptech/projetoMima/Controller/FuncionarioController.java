package school.sptech.projetoMima.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.Model.Funcionario;
import school.sptech.projetoMima.Repository.FuncionarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Operation(summary = "Buscar funcionário por ID")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Funcionário encontrado"), @ApiResponse(responseCode = "404", description = "Funcionário não encontrado") })
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarFuncionario(@PathVariable int id) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElse(null);
        if (funcionario == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(funcionario);
    }

    @Operation(summary = "Listar todos os funcionários")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Funcionários listados com sucesso"), @ApiResponse(responseCode = "404", description = "Nenhum funcionário encontrado") })
    @GetMapping
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        for (Funcionario f : funcionarioRepository.findAll()) {
            funcionarios.add(f);
        }
        if (funcionarios.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(funcionarios);
    }

    @Operation(summary = "Atualizar dados de um funcionário")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso"), @ApiResponse(responseCode = "404", description = "Funcionário não encontrado") })
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@RequestBody(description = "Funcionário com dados atualizados", required = true, content = @Content(schema = @Schema(implementation = Funcionario.class))) @org.springframework.web.bind.annotation.RequestBody Funcionario funcionario, @PathVariable int id) {
        Optional<Funcionario> funcionarioExistente = funcionarioRepository.findById(id);
        if (funcionarioExistente.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        Funcionario funcionarioAtualizar = funcionarioExistente.get();
        if (funcionario.getNome() != null) {
            funcionarioAtualizar.setNome(funcionario.getNome());
        }
        if (funcionario.getCargo() != null) {
            funcionarioAtualizar.setCargo(funcionario.getCargo());
        }
        if (funcionario.getEmail() != null) {
            funcionarioAtualizar.setEmail(funcionario.getEmail());
        }
        if (funcionario.getTelefone() != null) {
            funcionarioAtualizar.setTelefone(funcionario.getTelefone());
        }
        funcionarioRepository.save(funcionarioAtualizar);
        return ResponseEntity.ok(funcionarioAtualizar);
    }

    @Operation(summary = "Inserir novo funcionário")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso"), @ApiResponse(responseCode = "400", description = "Dados inválidos"), @ApiResponse(responseCode = "422", description = "Funcionário com e-mail ou telefone já cadastrado") })
    @PostMapping
    public ResponseEntity<Funcionario> inserirFuncionario(@RequestBody(description = "Funcionário a ser inserido", required = true, content = @Content(schema = @Schema(implementation = Funcionario.class))) @org.springframework.web.bind.annotation.RequestBody Funcionario funcionario) {
        if (funcionario.getEmail() == null || funcionario.getTelefone() == null) {
            return ResponseEntity.status(400).build();
        }
        if (funcionarioRepository.existsByEmail(funcionario.getEmail()) || funcionarioRepository.existsByTelefone(funcionario.getTelefone())) {
            return ResponseEntity.status(422).build();
        }
        Funcionario novoFuncionario = funcionarioRepository.save(funcionario);
        return ResponseEntity.status(201).body(novoFuncionario);
    }

    @Operation(summary = "Deletar funcionário por ID")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Funcionário deletado com sucesso"), @ApiResponse(responseCode = "404", description = "Funcionário não encontrado") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable int id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }
}
