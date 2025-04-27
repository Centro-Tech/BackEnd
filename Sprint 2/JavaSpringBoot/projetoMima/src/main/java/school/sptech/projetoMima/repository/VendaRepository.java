package school.sptech.projetoMima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.projetoMima.entity.Cliente;
import school.sptech.projetoMima.entity.ItemVenda;
import school.sptech.projetoMima.entity.Venda;

import java.time.LocalDate;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
    void deleteByItemVenda(ItemVenda itemParaDeletar);

    List<Venda> findByDataBetween(LocalDate inicio, LocalDate fim);

    List<Venda> findByCliente(Cliente cliente);

    List<Venda> findByValorTotal(Double valorMinimo, Double valorMax);
}
