package school.sptech.projetoMima.infrastructure.persistence;

import org.springframework.stereotype.Component;
import school.sptech.projetoMima.core.adapter.cliente.ClienteMutation;
import school.sptech.projetoMima.core.adapter.cliente.ClienteRead;
import school.sptech.projetoMima.core.domain.Cliente;
import school.sptech.projetoMima.SemCleanArch.repositorySemCleanArch.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteJpaAdapter implements ClienteRead, ClienteMutation {

    private final ClienteRepository repository;

    public ClienteJpaAdapter(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Cliente> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public void deletarPorId(Integer id) {
        repository.deleteById(id);
    }
}