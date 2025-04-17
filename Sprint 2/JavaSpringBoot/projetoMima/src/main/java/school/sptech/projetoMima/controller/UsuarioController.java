package school.sptech.projetoMima.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.dto.usuarioDto.UsuarioCadastroDto;
import school.sptech.projetoMima.dto.usuarioDto.UsuarioResumidoDto;
import school.sptech.projetoMima.dto.usuarioDto.UsuarioMapper;
import school.sptech.projetoMima.entity.Usuario;
import school.sptech.projetoMima.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Buscar usuários por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado", content = @Content(schema = @Schema(implementation = UsuarioResumidoDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResumidoDto> buscarPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.findFuncionarioById(id);
        UsuarioResumidoDto dto = UsuarioMapper.toResumidoDto(usuario);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Listar todos os funcionários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso", content = @Content(schema = @Schema(implementation = UsuarioResumidoDto.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado")
    })
    @GetMapping
    public ResponseEntity<List<UsuarioResumidoDto>> listar() {
        List<Usuario> usuarios = usuarioService.listarFuncionarios();
        List<UsuarioResumidoDto> response = usuarios.stream()
                .map(UsuarioMapper::toResumidoDto)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Inserir novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioCadastroDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<UsuarioResumidoDto> cadastrar(@RequestBody UsuarioCadastroDto dto) {
        Usuario novoUsuario = usuarioService.cadastrarFuncionario(dto);
        return ResponseEntity.status(201).body(UsuarioMapper.toResumidoDto(novoUsuario));
    }

    @Operation(summary = "Atualizar Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioCadastroDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResumidoDto> atualizar(@RequestBody UsuarioCadastroDto dto, @PathVariable Integer id) {
        Usuario usuarioAtualizado = usuarioService.atualizarFuncionario(dto, id);
        UsuarioResumidoDto dtoResposta = UsuarioMapper.toResumidoDto(usuarioAtualizado);
        return ResponseEntity.ok(dtoResposta);
    }

    @Operation(summary = "Deletar usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        usuarioService.excluir(id);
        return ResponseEntity.status(204).build();
    }
}
