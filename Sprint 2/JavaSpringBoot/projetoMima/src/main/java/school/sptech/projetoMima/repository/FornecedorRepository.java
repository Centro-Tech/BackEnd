package school.sptech.projetoMima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.entity.Fornecedor;

import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
    boolean existsByCnpj(String cnpj);

    void delete(Optional<Fornecedor> fornecedor);
}
