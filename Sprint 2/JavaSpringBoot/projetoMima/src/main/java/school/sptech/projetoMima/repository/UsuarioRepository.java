package school.sptech.projetoMima.repository;

import org.springframework.data.repository.CrudRepository;
import school.sptech.projetoMima.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    boolean existsByEmail(String email);

    boolean existsByTelefone(String telefone);

    Optional<Usuario> findByEmail(String email);

}
