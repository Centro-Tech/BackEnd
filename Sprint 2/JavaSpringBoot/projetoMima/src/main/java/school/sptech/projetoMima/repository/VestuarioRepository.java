package school.sptech.projetoMima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.versãoAntiga.Vestuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VestuarioRepository extends JpaRepository<Vestuario, Integer> {
    List<Vestuario> findVestuarioByNomeContainingIgnoreCase(String nome);
    Boolean existsByCodigoIdentificacao(String codigoIdentificacao);
    List<Vestuario> findVestuarioById(Integer id);

    List<Vestuario> findVestuarioByCodigoIdentificacaoIgnoreCase(String valor);

    List<Vestuario> findVestuarioByDataVendaBetween(LocalDate inicio, LocalDate fim);
    List<Vestuario> findByFornecedorId(Integer fornecedorId);

    List<Vestuario> findVestuarioByQuantidadeVendidaGreaterThan(Integer i);
    Optional<Vestuario> findByCodigoIdentificacaoAndQuantidadeVendidaGreaterThan(String codigoIdentificacao, int i);
}
