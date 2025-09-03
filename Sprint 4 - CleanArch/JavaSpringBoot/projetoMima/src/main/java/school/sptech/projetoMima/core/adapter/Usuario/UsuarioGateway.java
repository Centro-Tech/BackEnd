package school.sptech.projetoMima.core.adapter.Usuario;

public interface UsuarioGateway {
    Optional<Usuario> findById(Integer id);
    List<Usuario> findAll();
    Usuario save(Usuario usuario);
    void deleteById(Integer id);

    boolean existsById(Integer id);
    boolean existsByEmail(String email);
    boolean existsByTelefone(String telefone);
    Optional<Usuario> findByEmail(String email);
}
