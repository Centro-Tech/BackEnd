package school.sptech.projetoMima.core.adapter.cliente;

import school.sptech.projetoMima.core.domain.Cliente;

import java.util.List;
import java.util.Optional;


public interface ClienteRead {
    Optional<Cliente> buscarPorId(Integer id);
    List<Cliente> buscarTodos();
}
