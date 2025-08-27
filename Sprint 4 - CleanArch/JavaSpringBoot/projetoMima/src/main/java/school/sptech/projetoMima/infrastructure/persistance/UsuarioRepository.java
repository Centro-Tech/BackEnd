package school.sptech.projetoMima.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.core.domain.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByEmail(String email);

    boolean existsByTelefone(String telefone);

    Optional<Usuario> findByEmail(String email);

}
