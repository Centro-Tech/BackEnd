package school.sptech.projetoMima.core.adapter.cliente;

import school.sptech.projetoMima.core.domain.Cliente;

public interface ClienteMutation {
    Cliente salvar(Cliente cliente);
    void deletarPorId(Integer id);
}
