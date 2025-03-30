package school.sptech.projetoMima.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.Model.Vestuario;

import java.time.LocalDate;
import java.util.List;

public interface VestuarioRepository extends JpaRepository<Vestuario, Integer> {
    List<Vestuario> findVestuarioByNomeContainingIgnoreCase(String nome);
    Boolean existsByCodigoIdentificacao(String codigoIdentificacao);
    List<Vestuario> findVestuarioById(Integer id);

    List<Vestuario> findVestuarioByCodigoIdentificacaoIgnoreCase(String valor);

    List<Vestuario> findVestuarioByDataVendaBetween(LocalDate inicio, LocalDate fim);
    List<Vestuario> findByFornecedorId(Integer fornecedorId);
}
