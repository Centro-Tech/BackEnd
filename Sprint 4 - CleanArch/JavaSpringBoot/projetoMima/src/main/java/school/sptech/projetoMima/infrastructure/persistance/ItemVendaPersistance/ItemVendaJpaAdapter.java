package school.sptech.projetoMima.infrastructure.persistance.ItemVendaPersistance;

import school.sptech.projetoMima.core.adapter.ItemVenda.ItemVendaGateway;
import school.sptech.projetoMima.core.domain.ItemVenda;

import java.util.List;
import java.util.Optional;

public class ItemVendaJpaAdapter implements ItemVendaGateway {

    private final ItemVendaJpaRepository itemVendaJpaRepository;

    public ItemVendaJpaAdapter(ItemVendaJpaRepository itemVendaJpaRepository,
                              ItemVendaEntityMapper itemVendaEntityMapper) {
        this.itemVendaJpaRepository = itemVendaJpaRepository;
    }

    @Override
    public ItemVenda save(ItemVenda itemVenda) {
        ItemVendaEntity jpaEntity = ItemVendaEntityMapper.toEntity(itemVenda);
        ItemVendaEntity savedEntity = itemVendaJpaRepository.save(jpaEntity);
        return ItemVendaEntityMapper.toDomain(savedEntity);
    }

    @Override
    public boolean existsById(Integer id) {
        return itemVendaJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        itemVendaJpaRepository.deleteById(id);
    }

    @Override
    public List<ItemVenda> findAll() {
        return itemVendaJpaRepository.findAll()
                .stream()
                .map(ItemVendaEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<ItemVenda> findById(Integer id) {
        return itemVendaJpaRepository.findById(id)
                .map(ItemVendaEntityMapper::toDomain);
    }

    @Override
    public List<ItemVenda> findByClienteIdAndVendaIsNull(Integer clienteId) {
        return itemVendaJpaRepository.findByClienteIdAndVendaIsNull(clienteId)
                .stream()
                .map(ItemVendaEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<ItemVenda> buscarPorIdEVenda(Integer idItemVenda, Integer idVenda) {
        return itemVendaJpaRepository.buscarPorIdEVenda(idItemVenda, idVenda)
                .map(ItemVendaEntityMapper::toDomain);
    }
}
