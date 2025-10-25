package school.sptech.projetoMima.infrastructure.persistance.VendaPersistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.projetoMima.core.domain.Venda;

import java.time.LocalDate;
import java.util.List;

public interface VendaJpaRepository extends JpaRepository<VendaEntity, Integer> {

    List<VendaEntity> findByCliente_IdCliente(Integer clienteId);

    List<VendaEntity> findByUsuario_Id(Integer usuarioId);

    List<VendaEntity> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);

    List<VendaEntity> findByValorTotalBetween(Double valorMinimo, Double valorMaximo);

    @Query(value = "SELECT SUM(valor_total) FROM Venda WHERE DATE(data) = CURDATE()", nativeQuery = true)
    Double findTotalVendidoHoje();

    @Query(value = "SELECT COUNT(*) FROM Venda WHERE DATE(data) = CURDATE()", nativeQuery = true)
    Long findQuantidadeVendasHoje();

    @Query(value = "SELECT COUNT(DISTINCT fkCliente) " +
            "FROM Venda " +
            "WHERE data >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)", nativeQuery = true)
    Long findClientesUltimoMes();

}
