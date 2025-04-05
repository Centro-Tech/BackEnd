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
import school.sptech.projetoMima.Model.ProprietarioSocio;
import school.sptech.projetoMima.Repository.ProprietarioSocioRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioSocioController {

    @Autowired
    private ProprietarioSocioRepository proprietarioSocioRepository;

    @Operation(summary = "Buscar um proprietário ou sócio pelo ID") @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Proprietário encontrado", content = @Content(schema = @Schema(implementation = ProprietarioSocio.class))), @ApiResponse(responseCode = "404", description = "Proprietário não encontrado")})
    @GetMapping("/{id}")
    public ResponseEntity<ProprietarioSocio> getProprietarioSocio(@PathVariable int id) {
        Optional<ProprietarioSocio> proprietarioExistente = proprietarioSocioRepository.findById(id);
        if(proprietarioExistente.isPresent()) {
            return ResponseEntity.ok(proprietarioExistente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Listar todos os proprietários e sócios") @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de proprietários retornada com sucesso", content = @Content(schema = @Schema(implementation = ProprietarioSocio.class))), @ApiResponse(responseCode = "404", description = "Nenhum proprietário encontrado")})
    @GetMapping
    public ResponseEntity<List<ProprietarioSocio>> listarProprietarios() {
        List<ProprietarioSocio> proprietarios = proprietarioSocioRepository.findAll();
        if (proprietarios.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(proprietarios);
    }

    @Operation(summary = "Atualizar os dados de um proprietário ou sócio") @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Dados atualizados com sucesso", content = @Content(schema = @Schema(implementation = ProprietarioSocio.class))), @ApiResponse(responseCode = "404", description = "Proprietário não encontrado")})
    @PutMapping("/{id}")
    public ResponseEntity<ProprietarioSocio> atualizarDados(@RequestBody(description = "Dados atualizados do proprietário", required = true, content = @Content(schema = @Schema(implementation = ProprietarioSocio.class))) @org.springframework.web.bind.annotation.RequestBody ProprietarioSocio proprietarioSocio, @PathVariable int id) {
        Optional<ProprietarioSocio> proprietarioExistente = proprietarioSocioRepository.findById(id);
        if (proprietarioExistente.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        ProprietarioSocio pessoaExistente = proprietarioExistente.get();
        if (proprietarioSocio.getCpf() != null) pessoaExistente.setCpf(proprietarioSocio.getCpf());
        if (proprietarioSocio.getNome() != null) pessoaExistente.setNome(proprietarioSocio.getNome());
        if (proprietarioSocio.getPapel() != null) pessoaExistente.setPapel(proprietarioSocio.getPapel());
        if (proprietarioSocio.getEmail() != null) pessoaExistente.setEmail(proprietarioSocio.getEmail());
        if (proprietarioSocio.getTelefone() != null) pessoaExistente.setTelefone(proprietarioSocio.getTelefone());
        proprietarioSocioRepository.save(pessoaExistente);
        return ResponseEntity.ok(pessoaExistente);
    }

    @Operation(summary = "Cadastrar um novo proprietário ou sócio") @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Proprietário cadastrado com sucesso", content = @Content(schema = @Schema(implementation = ProprietarioSocio.class))), @ApiResponse(responseCode = "400", description = "Requisição inválida")})
    @PostMapping
    public ResponseEntity<ProprietarioSocio> cadastrar(@RequestBody(description = "Dados do novo proprietário", required = true, content = @Content(schema = @Schema(implementation = ProprietarioSocio.class))) @org.springframework.web.bind.annotation.RequestBody ProprietarioSocio proprietarioSocio) {
        if (proprietarioSocio == null) {
            return ResponseEntity.status(400).build();
        }
        proprietarioSocioRepository.save(proprietarioSocio);
        return ResponseEntity.status(201).body(proprietarioSocio);
    }

    @Operation(summary = "Excluir um proprietário ou sócio") @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Proprietário excluído com sucesso"), @ApiResponse(responseCode = "404", description = "Proprietário não encontrado")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        if (proprietarioSocioRepository.existsById(id)) {
            proprietarioSocioRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }
}
