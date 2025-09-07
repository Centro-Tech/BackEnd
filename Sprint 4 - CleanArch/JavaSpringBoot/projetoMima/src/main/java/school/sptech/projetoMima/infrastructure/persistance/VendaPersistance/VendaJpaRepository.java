package school.sptech.projetoMima.infrastructure.persistance.VendaPersistance;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.core.domain.Venda;

import java.time.LocalDate;
import java.util.List;

public interface VendaJpaRepository extends JpaRepository<VendaEntity, Integer> {

    List<VendaEntity> findByClienteId(Integer clienteId);

    List<VendaEntity> findByUsuarioId(Integer usuarioId);

    List<VendaEntity> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);

    List<VendaEntity> findByValorTotalBetween(Double valorMinimo, Double valorMaximo);
}
