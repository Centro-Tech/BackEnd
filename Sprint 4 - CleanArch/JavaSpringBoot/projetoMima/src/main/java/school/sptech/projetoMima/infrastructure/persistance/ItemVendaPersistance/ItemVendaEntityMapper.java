package school.sptech.projetoMima.infrastructure.persistance.ItemVendaPersistance;

import school.sptech.projetoMima.core.domain.ItemVenda;
import school.sptech.projetoMima.core.domain.Cliente;
import school.sptech.projetoMima.core.domain.Usuario;
import school.sptech.projetoMima.core.domain.Venda;
import school.sptech.projetoMima.core.domain.Fornecedor;
import school.sptech.projetoMima.infrastructure.persistance.ClientePersistance.Enitity.ClienteEntity;
import school.sptech.projetoMima.infrastructure.persistance.UsuarioPersistance.UsuarioEntity;
import school.sptech.projetoMima.infrastructure.persistance.VendaPersistance.VendaEntity;
import school.sptech.projetoMima.infrastructure.persistance.FornecedorPersistance.FornecedorEntity;

public class ItemVendaEntityMapper {

    public static ItemVendaEntity toEntity(ItemVenda domainEntity) {
        if (domainEntity == null) return null;

        ItemVendaEntity entity = new ItemVendaEntity();
        entity.setId(domainEntity.getId());
        entity.setQtdParaVender(domainEntity.getQtdParaVender());

        if (domainEntity.getItem() != null) {
            entity.setItem(domainEntity.getItem());
        }

        if (domainEntity.getCliente() != null) {
            ClienteEntity clienteEntity = new ClienteEntity();
            clienteEntity.setIdCliente(domainEntity.getCliente().getId());
            entity.setCliente(clienteEntity);
        }

        if (domainEntity.getFuncionario() != null) {
            UsuarioEntity funcionarioEntity = new UsuarioEntity();
            funcionarioEntity.setNome(domainEntity.getFuncionario().getNome());
            funcionarioEntity.setEmail(domainEntity.getFuncionario().getEmail());
            entity.setFuncionario(funcionarioEntity);
        }

        if (domainEntity.getFornecedor() != null) {
            FornecedorEntity fornecedorEntity = new FornecedorEntity();
            fornecedorEntity.setId(domainEntity.getFornecedor().getId());
            entity.setFornecedor(fornecedorEntity);
        }

        if (domainEntity.getVenda() != null) {
            VendaEntity vendaEntity = new VendaEntity();
            vendaEntity.setId(domainEntity.getVenda().getId());
            entity.setVenda(vendaEntity);
        }

        return entity;
    }

    public static ItemVenda toDomain(ItemVendaEntity entity) {
        if (entity == null) return null;

        ItemVenda domainEntity = new ItemVenda();
        domainEntity.setId(entity.getId());
        domainEntity.setQtdParaVender(entity.getQtdParaVender());

        if (entity.getItem() != null) {
            domainEntity.setItem(entity.getItem());
        }

        if (entity.getCliente() != null) {
            Cliente cliente = new Cliente();
            cliente.setId(entity.getCliente().getIdCliente());
            domainEntity.setCliente(cliente);
        }

        if (entity.getFuncionario() != null) {
            Usuario funcionario = new Usuario(
                entity.getFuncionario().getNome(),
                entity.getFuncionario().getEmail(),
                null, null, null, null
            );
            domainEntity.setFuncionario(funcionario);
        }

        if (entity.getFornecedor() != null) {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(entity.getFornecedor().getId());
            domainEntity.setFornecedor(fornecedor);
        }

        if (entity.getVenda() != null) {
            Venda venda = new Venda();
            venda.setId(entity.getVenda().getId());
            domainEntity.setVenda(venda);
        }

        return domainEntity;
    }
}
