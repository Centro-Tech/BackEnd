package school.sptech.projetoMima.repository;

import org.springframework.data.repository.CrudRepository;
import school.sptech.projetoMima.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    boolean existsByEmail(String email);

    boolean existsByTelefone(String telefone);

}
