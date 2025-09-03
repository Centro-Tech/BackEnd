package school.sptech.projetoMima.infrastructure.persistance.VendaPersistance;

import school.sptech.projetoMima.core.adapter.Venda.VendaGateway;
import school.sptech.projetoMima.core.domain.Venda;
import school.sptech.projetoMima.infrastructure.persistance.VendaRepository;

import java.util.List;

public class VendaJpaAdapter implements VendaGateway {

    private final VendaRepository vendaRepository;

    public VendaJpaAdapter(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @Override
    public Venda save(Venda venda) {
        return null;
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<Venda> findAll() {
        return List.of();
    }

    @Override
    public Venda findById(Integer id) {
        return null;
    }

    @Override
    public List<Venda> findByClienteId(Integer clienteId) {
        return List.of();
    }

    @Override
    public List<Venda> findByUsuarioId(Integer usuarioId) {
        return List.of();
    }
}
