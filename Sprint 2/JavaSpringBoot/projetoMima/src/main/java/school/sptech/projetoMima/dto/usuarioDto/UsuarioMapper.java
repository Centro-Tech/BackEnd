package school.sptech.projetoMima.dto.usuarioDto;

import school.sptech.projetoMima.entity.Usuario;

public class UsuarioMapper {


    public static Usuario toEntity(UsuarioCadastroDto funcionario) {
        Usuario usuarioCadastro = new Usuario();
        usuarioCadastro.setNome(funcionario.getNome());
        usuarioCadastro.setEmail(funcionario.getEmail());
        usuarioCadastro.setTelefone(funcionario.getTelefone());
        usuarioCadastro.setEndereco(funcionario.getEndereco());
        return usuarioCadastro;
    }

    public static UsuarioResumidoDto toResumidoDto(Usuario usuario) {
        UsuarioResumidoDto usuarioResumidoDto = new UsuarioResumidoDto();
        usuarioResumidoDto.setCargo(usuario.getCargo());
        usuarioResumidoDto.setNome(usuario.getNome());

        return usuarioResumidoDto;
    }

    public static UsuarioListagemDto toListagemDto(Usuario usuario) {
        UsuarioListagemDto listagemDto = new UsuarioListagemDto();
        listagemDto.setNome(usuario.getNome());
        listagemDto.setEmail(usuario.getEmail());
        return listagemDto;
    }

}
