package school.sptech.projetoMima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
