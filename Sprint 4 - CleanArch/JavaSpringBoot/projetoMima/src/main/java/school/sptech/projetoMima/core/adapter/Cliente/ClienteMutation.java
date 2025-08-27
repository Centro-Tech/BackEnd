package school.sptech.projetoMima.core.adapter.Cliente;

import school.sptech.projetoMima.core.domain.Cliente;

import java.util.List;

public interface ClienteMutation {
    Cliente save(Cliente cliente);
    boolean existsByCpf(String cpf);

    boolean existsById(Integer id);

    void deleteById(Integer id);

    List<Cliente> findAll();

    Cliente findById(Integer id);
}
