package school.sptech.projetoMima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.versãoAntiga.FornecedorAntigo;

import java.time.LocalDate;
import java.util.List;

public interface FornecedorRepository extends JpaRepository<FornecedorAntigo, Integer> {
    boolean existsByCnpj(String cnpj);


    List<FornecedorAntigo> findFornecedorByDataRegistroBetween(LocalDate inicio, LocalDate fim);
}
