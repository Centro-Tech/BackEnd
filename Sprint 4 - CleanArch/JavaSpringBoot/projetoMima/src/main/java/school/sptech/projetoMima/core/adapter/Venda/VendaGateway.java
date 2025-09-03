package school.sptech.projetoMima.core.adapter.Venda;

import school.sptech.projetoMima.core.domain.Venda;

import java.util.List;

public interface VendaGateway {
    Venda save(Venda venda);
    boolean existsById (Integer id);

    void deleteById(Integer id);

    List<Venda> findAll();

    Venda findById(Integer id);

    List<Venda> findByClienteId(Integer clienteId);

    List<Venda> findByUsuarioId(Integer usuarioId);
}
