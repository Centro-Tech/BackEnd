package school.sptech.projetoMima.core.application.dto.usuarioDto;

import school.sptech.projetoMima.core.domain.Usuario;

public class UsuarioMapper {

    public static Usuario fromCadastroDto(UsuarioCadastroDto dto) {
        return new Usuario(
                dto.getNome(),
                dto.getEmail(),
                dto.getTelefone(),
                dto.getEndereco(),
                dto.getSenha(),
                dto.getCargo()
        );
    }

    public static Usuario fromCriacaoDto(UsuarioCriacaoDto dto) {
        return new Usuario(
                dto.getNome(),
                dto.getEmail(),
                null, // telefone opcional
                null, // endereco opcional
                dto.getSenha(),
                null  // cargo opcional
        );
    }

    public static Usuario fromLoginDto(UsuarioLoginDto dto) {
        return new Usuario(
                null,
                dto.getEmail(),
                null,
                null,
                dto.getSenha(),
                null
        );
    }

    public static Usuario fromDetalhesDto(UsuarioDetalhesDto dto) {
        return new Usuario(
                dto.getNome(),
                dto.getUsername(),
                null,
                null,
                dto.getPassword(),
                null
        );
    }

    public static UsuarioResumidoDto toResumidoDto(Usuario usuario) {
        UsuarioResumidoDto dto = new UsuarioResumidoDto();
        dto.setNome(usuario.getNome());
        dto.setCargo(usuario.getCargo());
        return dto;
    }

    public static UsuarioListagemDto toListagemDto(Usuario usuario) {
        UsuarioListagemDto dto = new UsuarioListagemDto();
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setCargo(usuario.getCargo());
        return dto;
    }

    public static UsuarioListarDto toListarDto(Usuario usuario) {
        UsuarioListarDto dto = new UsuarioListarDto();
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setSenha(usuario.getSenha());
        return dto;
    }

    public static UsuarioTokenDto toTokenDto(Usuario usuario, String token) {
        UsuarioTokenDto dto = new UsuarioTokenDto();
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setToken(token);
        return dto;
    }
}
