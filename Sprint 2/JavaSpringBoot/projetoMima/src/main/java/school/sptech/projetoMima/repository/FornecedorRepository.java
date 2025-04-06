package school.sptech.projetoMima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.versãoAntiga.Fornecedor;

import java.time.LocalDate;
import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
    boolean existsByCnpj(String cnpj);

    List<Fornecedor> findByDataRegistroBetween(LocalDate inicio, LocalDate fim);

    List<Fornecedor> findFornecedorByDataRegistroBetween(LocalDate inicio, LocalDate fim);
}
