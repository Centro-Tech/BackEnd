package school.sptech.projetoMima.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.versãoAntiga.FornecedorAntigo;
import school.sptech.projetoMima.versãoAntiga.Vestuario;
import school.sptech.projetoMima.repository.FornecedorRepository;
import school.sptech.projetoMima.repository.VestuarioRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private VestuarioRepository vestuarioRepository;

    @Operation(summary = "Listar todos os fornecedores")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Fornecedores encontrados"), @ApiResponse(responseCode = "404", description = "Nenhum fornecedor encontrado") })
    @GetMapping
    public ResponseEntity<List<FornecedorAntigo>> listar() {
        List<FornecedorAntigo> fornecedores = fornecedorRepository.findAll();
        if (fornecedores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fornecedores);
    }

    @Operation(summary = "Buscar fornecedor por ID")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Fornecedor encontrado"), @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado") })
    @GetMapping("/{id}")
    public ResponseEntity<FornecedorAntigo> buscar(@PathVariable int id) {
        Optional<FornecedorAntigo> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isPresent()) {
            FornecedorAntigo fornecedorNovo = fornecedor.get();
            return ResponseEntity.ok(fornecedorNovo);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Filtrar fornecedores por data de registro")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Fornecedores encontrados"), @ApiResponse(responseCode = "404", description = "Nenhum fornecedor encontrado no período") })
    @GetMapping("/filtro-data-registro")
    public ResponseEntity<List<FornecedorAntigo>> filtrarPorDataRegistro(@RequestParam("inicio") LocalDate inicio, @RequestParam("fim") LocalDate fim) {
        List<FornecedorAntigo> fornecedores = fornecedorRepository.findFornecedorByDataRegistroBetween(inicio, fim);
        if (fornecedores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(200).body(fornecedores);
    }

    @Operation(summary = "Buscar vestuários de um fornecedor")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Vestuários encontrados"), @ApiResponse(responseCode = "404", description = "Nenhum vestuário encontrado para este fornecedor") })
    @GetMapping("/{fornecedorId}/vestuarios")
    public ResponseEntity<List<Vestuario>> buscarVestuariosPorFornecedor(@PathVariable Integer fornecedorId) {
        List<Vestuario> vestuarios = vestuarioRepository.findByFornecedorId(fornecedorId);
        if (vestuarios.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(vestuarios);
    }

    @Operation(summary = "Cadastrar novo fornecedor")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Fornecedor cadastrado com sucesso"), @ApiResponse(responseCode = "400", description = "Fornecedor já existente") })
    @PostMapping
    public ResponseEntity<FornecedorAntigo> cadastrar(@RequestBody(description = "Fornecedor a ser cadastrado", required = true, content = @Content(schema = @Schema(implementation = FornecedorAntigo.class))) @org.springframework.web.bind.annotation.RequestBody FornecedorAntigo fornecedorAntigo) {
        if (fornecedorRepository.existsByCnpj(fornecedorAntigo.getCnpj())) {
            return ResponseEntity.badRequest().body(fornecedorAntigo);
        }
        return ResponseEntity.ok(fornecedorRepository.save(fornecedorAntigo));
    }

    @Operation(summary = "Atualizar fornecedor")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Fornecedor atualizado com sucesso"), @ApiResponse(responseCode = "409", description = "Fornecedor não encontrado") })
    @PutMapping("/{id}")
    public ResponseEntity<FornecedorAntigo> atualizar(@PathVariable int id, @RequestBody(description = "Dados atualizados do fornecedor", required = true, content = @Content(schema = @Schema(implementation = FornecedorAntigo.class))) @org.springframework.web.bind.annotation.RequestBody FornecedorAntigo fornecedorAntigo) {
        Optional<FornecedorAntigo> fornecedorAtual = fornecedorRepository.findById(id);
        if (fornecedorAtual.isPresent()) {
            FornecedorAntigo fornecedorAntigoNovo = fornecedorAtual.get();
            if (fornecedorAntigo.getNome() != null) {
                fornecedorAntigoNovo.setNome(fornecedorAntigo.getNome());
            }
            if (fornecedorAntigo.getCnpj() != null) {
                fornecedorAntigoNovo.setCnpj(fornecedorAntigo.getCnpj());
            }
            if (fornecedorAntigo.getEndereco() != null) {
                fornecedorAntigoNovo.setEndereco(fornecedorAntigo.getEndereco());
            }
            if (fornecedorAntigo.getTelefone() != null) {
                fornecedorAntigoNovo.setTelefone(fornecedorAntigo.getTelefone());
            }
            if (fornecedorAntigo.getEmail() != null) {
                fornecedorAntigoNovo.setEmail(fornecedorAntigo.getEmail());
            }
            fornecedorRepository.save(fornecedorAntigoNovo);
            return ResponseEntity.ok(fornecedorAntigoNovo);
        }
        return ResponseEntity.status(409).build();
    }

    @Operation(summary = "Excluir fornecedor")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Fornecedor excluído com sucesso"), @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado") })
    @DeleteMapping("/{id}")
    public ResponseEntity<FornecedorAntigo> excluir(@PathVariable int id) {
        Optional<FornecedorAntigo> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isPresent()) {
            fornecedorRepository.deleteById(id);
            return ResponseEntity.ok(fornecedor.get());
        }
        return ResponseEntity.notFound().build();
    }
}
