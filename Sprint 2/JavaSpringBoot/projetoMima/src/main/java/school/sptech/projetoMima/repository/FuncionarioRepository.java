package school.sptech.projetoMima.repository;

import org.springframework.data.repository.CrudRepository;
import school.sptech.projetoMima.versãoAntiga.Funcionario;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {

    boolean existsByEmail(String email);

    boolean existsByTelefone(String telefone);

}
