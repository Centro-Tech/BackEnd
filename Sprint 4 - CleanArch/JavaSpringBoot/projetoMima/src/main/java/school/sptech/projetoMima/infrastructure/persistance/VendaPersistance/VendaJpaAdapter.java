package school.sptech.projetoMima.infrastructure.persistance.VendaPersistance;

import school.sptech.projetoMima.core.adapter.Venda.VendaGateway;
import school.sptech.projetoMima.core.domain.Venda;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VendaJpaAdapter implements VendaGateway {

    private final VendaJpaRepository vendaJpaRepository;
    private final VendaEntityMapper vendaEntityMapper;

    public VendaJpaAdapter(VendaJpaRepository vendaJpaRepository, VendaEntityMapper vendaEntityMapper) {
        this.vendaJpaRepository = vendaJpaRepository;
        this.vendaEntityMapper = vendaEntityMapper;
    }

    @Override
    public Venda save(Venda venda) {
        VendaEntity vendaEntity = vendaEntityMapper.toEntity(venda);
        VendaEntity savedEntity = vendaJpaRepository.save(vendaEntity);
        return vendaEntityMapper.toDomain(savedEntity);
    }

    @Override
    public boolean existsById(Integer id) {
        return vendaJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        vendaJpaRepository.deleteById(id);
    }

    @Override
    public List<Venda> findAll() {
        return vendaJpaRepository.findAll()
                .stream()
                .map(vendaEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Venda findById(Integer id) {
        Optional<VendaEntity> vendaEntity = vendaJpaRepository.findById(id);
        return vendaEntity.map(vendaEntityMapper::toDomain).orElse(null);
    }

    @Override
    public List<Venda> findByClienteId(Integer clienteId) {
        return vendaJpaRepository.findByClienteId(clienteId)
                .stream()
                .map(vendaEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venda> findByUsuarioId(Integer usuarioId) {
        return vendaJpaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(vendaEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venda> findByDataBetween(LocalDate dataInicio, LocalDate dataFim) {
        return vendaJpaRepository.findByDataBetween(dataInicio, dataFim)
                .stream()
                .map(vendaEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venda> findByValorTotalBetween(Double valorMinimo, Double valorMaximo) {
        return vendaJpaRepository.findByValorTotalBetween(valorMinimo, valorMaximo)
                .stream()
                .map(vendaEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
