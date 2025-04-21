package school.sptech.projetoMima.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.dto.usuarioDto.usuarioLogin.*;
import school.sptech.projetoMima.entity.Usuario;
import school.sptech.projetoMima.service.UsuarioLoginService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioLoginController {

    @Autowired
    private UsuarioLoginService usuarioLoginService;

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {

        final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);
        this.usuarioLoginService.criar(novoUsuario);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {

        final Usuario usuario = UsuarioMapper.of(usuarioLoginDto);
        UsuarioTokenDto usuarioTokenDto = this.usuarioLoginService.autenticar(usuario);

        return ResponseEntity.status(200).body(usuarioTokenDto);
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<UsuarioListarDto>> listarTodos() {

        List<UsuarioListarDto> usuariosEncontrados = this.usuarioLoginService.listarTodos();

        if (usuariosEncontrados.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuariosEncontrados);

    }
}
