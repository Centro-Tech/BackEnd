package school.sptech.projetoMima.infrastructure.persistance.UsuarioPersistance;

import school.sptech.projetoMima.core.domain.Usuario;

public class UsuarioEntityMapper {

    public static UsuarioEntity toEntity(Usuario u) {
        UsuarioEntity e = new UsuarioEntity();
        e.setId(u.getId());
        e.setNome(u.getNome());
        e.setEmail(u.getEmail());
        e.setTelefone(u.getTelefone());
        e.setEndereco(u.getEndereco());
        e.setSenha(u.getSenha());
        e.setCargo(u.getCargo());
        e.setImagem(u.getImagem());
        return e;
    }

    public static Usuario toDomain(UsuarioEntity e) {
        return new Usuario(
                e.getId(),
                e.getNome(),
                e.getEmail(),
                e.getTelefone(),
                e.getEndereco(),
                e.getSenha(),
                e.getCargo(),
                e.getImagem()
        );
    }
}