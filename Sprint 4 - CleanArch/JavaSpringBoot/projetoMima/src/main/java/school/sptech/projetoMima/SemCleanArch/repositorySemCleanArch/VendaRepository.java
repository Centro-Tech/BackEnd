package school.sptech.projetoMima.SemCleanArch.repositorySemCleanArch;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.core.domain.Cliente;
import school.sptech.projetoMima.core.domain.Venda;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VendaRepository extends JpaRepository<Venda, Integer> {

    List<Venda> findByDataBetween(LocalDate inicio, LocalDate fim);

    List<Venda> findByCliente(Cliente cliente);

    List<Venda> findByValorTotalBetween(Double valorMin, Double valorMax);

    Optional<Integer> findTopByClienteIdOrderByIdDesc(Integer clienteId);
}
