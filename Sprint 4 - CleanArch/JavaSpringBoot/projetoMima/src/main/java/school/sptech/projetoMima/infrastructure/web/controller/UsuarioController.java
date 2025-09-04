package school.sptech.projetoMima.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import school.sptech.projetoMima.core.application.command.Usuario.*;
import school.sptech.projetoMima.core.application.dto.usuarioDto.*;
import school.sptech.projetoMima.core.application.usecase.Usuario.*;
import school.sptech.projetoMima.core.domain.Usuario;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final ListarUsuariosUseCase listarUsuariosUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    private final ExcluirUsuarioUseCase excluirUsuarioUseCase;
    private final AutenticarUsuarioUseCase autenticarUsuarioUseCase;
    private final TrocarSenhaUseCase trocarSenhaUseCase;

    public UsuarioController(CriarUsuarioUseCase criarUsuarioUseCase,
                             AtualizarUsuarioUseCase atualizarUsuarioUseCase,
                             ListarUsuariosUseCase listarUsuariosUseCase,
                             BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase,
                             ExcluirUsuarioUseCase excluirUsuarioUseCase,
                             AutenticarUsuarioUseCase autenticarUsuarioUseCase,
                             TrocarSenhaUseCase trocarSenhaUseCase) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.excluirUsuarioUseCase = excluirUsuarioUseCase;
        this.autenticarUsuarioUseCase = autenticarUsuarioUseCase;
        this.trocarSenhaUseCase = trocarSenhaUseCase;
    }

    @Operation(summary = "Buscar usuário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(schema = @Schema(implementation = UsuarioResumidoDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResumidoDto> buscarPorId(@PathVariable Integer id) {
        Usuario usuario = buscarUsuarioPorIdUseCase.executar(id);
        return ResponseEntity.ok(UsuarioMapper.toResumidoDto(usuario));
    }

    @Operation(summary = "Listar todos os usuários")
    @GetMapping
    public ResponseEntity<List<UsuarioResumidoDto>> listar() {
        List<Usuario> usuarios = listarUsuariosUseCase.executar();
        List<UsuarioResumidoDto> response = usuarios.stream()
                .map(UsuarioMapper::toResumidoDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cadastrar funcionário com senha padrão")
    @PostMapping("/funcionarios")
    public ResponseEntity<UsuarioResumidoDto> cadastrarFuncionario(@RequestBody UsuarioCadastroDto dto) {
        Usuario usuarioDomain = UsuarioMapper.fromCadastroDto(dto);

        CriarUsuarioCommand cmd = new CriarUsuarioCommand(
                usuarioDomain.getNome(),
                usuarioDomain.getEmail(),
                null, // senha padrão
                usuarioDomain.getTelefone(),
                usuarioDomain.getCargo(),
                usuarioDomain.getEndereco()
        );

        Usuario novo = criarUsuarioUseCase.executar(cmd, true);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UsuarioMapper.toResumidoDto(novo));
    }

    @Operation(summary = "Criar novo usuário com senha informada")
    @SecurityRequirement(name = "Bearer")
    @PostMapping("/criar")
    public ResponseEntity<Void> criar(@RequestBody @Valid UsuarioCriacaoDto dto) {
        Usuario usuarioDomain = UsuarioMapper.fromCriacaoDto(dto);

        CriarUsuarioCommand cmd = new CriarUsuarioCommand(
                usuarioDomain.getNome(),
                usuarioDomain.getEmail(),
                usuarioDomain.getSenha(),
                usuarioDomain.getTelefone(),
                usuarioDomain.getCargo(),
                usuarioDomain.getEndereco()
        );

        criarUsuarioUseCase.executar(cmd, false);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Atualizar usuário")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResumidoDto> atualizar(@RequestBody UsuarioCadastroDto dto,
                                                        @PathVariable Integer id) {
        Usuario usuarioDomain = UsuarioMapper.fromCadastroDto(dto);

        AtualizarUsuarioCommand cmd = new AtualizarUsuarioCommand(
                id,
                usuarioDomain.getNome(),
                usuarioDomain.getEmail(),
                usuarioDomain.getTelefone(),
                usuarioDomain.getCargo(),
                usuarioDomain.getEndereco()
        );

        Usuario atualizado = atualizarUsuarioUseCase.executar(cmd);
        return ResponseEntity.ok(UsuarioMapper.toResumidoDto(atualizado));
    }

    @Operation(summary = "Deletar usuário por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        excluirUsuarioUseCase.executar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Realizar login de usuário")
    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto dto) {
        Usuario usuarioDomain = UsuarioMapper.fromLoginDto(dto);
        LoginUsuarioCommand cmd = new LoginUsuarioCommand(usuarioDomain.getEmail(), usuarioDomain.getSenha());

        AutenticarUsuarioUseCase.Resultado res = autenticarUsuarioUseCase.executar(cmd);
        UsuarioTokenDto tokenDto = UsuarioMapper.toTokenDto(res.usuario(), res.token());

        return ResponseEntity.ok(tokenDto);
    }

    @Operation(summary = "Trocar a senha do usuário autenticado")
    @PutMapping("/trocar-senha")
    public ResponseEntity<String> trocarSenha(@RequestBody TrocarSenhaDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUsuario = (authentication != null) ? authentication.getName() : "anonymousUser";

        if ("anonymousUser".equals(emailUsuario)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }
        TrocarSenhaCommand cmd = new TrocarSenhaCommand(
                emailUsuario,
                dto.getSenhaAtual(),
                dto.getNovaSenha()
        );
        trocarSenhaUseCase.executar(cmd);
        return ResponseEntity.ok("Senha alterada com sucesso");
    }
}
