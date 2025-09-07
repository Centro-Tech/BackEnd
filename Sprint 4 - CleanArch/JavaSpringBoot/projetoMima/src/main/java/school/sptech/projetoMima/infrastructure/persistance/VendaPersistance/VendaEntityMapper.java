package school.sptech.projetoMima.infrastructure.persistance.VendaPersistance;

import school.sptech.projetoMima.core.domain.Venda;
import school.sptech.projetoMima.core.domain.Cliente;
import school.sptech.projetoMima.core.domain.Usuario;
import school.sptech.projetoMima.infrastructure.persistance.ClientePersistance.Enitity.ClienteEntity;
import school.sptech.projetoMima.infrastructure.persistance.UsuarioPersistance.UsuarioEntity;

import java.util.ArrayList;

public class VendaEntityMapper {

    public VendaEntity toEntity(Venda venda) {
        if (venda == null) return null;

        VendaEntity vendaEntity = new VendaEntity();
        vendaEntity.setId(venda.getId());
        vendaEntity.setValorTotal(venda.getValorTotal());
        vendaEntity.setData(venda.getData());

        if (venda.getCliente() != null) {
            ClienteEntity clienteEntity = new ClienteEntity();
            clienteEntity.setIdCliente(venda.getCliente().getId());
            clienteEntity.setNome(venda.getCliente().getNome());
            clienteEntity.setCPF(venda.getCliente().getCPF());
            clienteEntity.setTelefone(venda.getCliente().getTelefone());
            clienteEntity.setEmail(venda.getCliente().getEmail());
            vendaEntity.setCliente(clienteEntity);
        }

        if (venda.getUsuario() != null) {
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            usuarioEntity.setNome(venda.getUsuario().getNome());
            usuarioEntity.setEmail(venda.getUsuario().getEmail());
            vendaEntity.setUsuario(usuarioEntity);
        }

        vendaEntity.setItensVenda(new ArrayList<>());
        return vendaEntity;
    }

    public Venda toDomain(VendaEntity entity) {
        if (entity == null) return null;

        Venda venda = new Venda();
        venda.setId(entity.getId());
        venda.setValorTotal(entity.getValorTotal());
        venda.setData(entity.getData());

        if (entity.getCliente() != null) {
            Cliente cliente = new Cliente();
            cliente.setId(entity.getCliente().getIdCliente());
            cliente.setNome(entity.getCliente().getNome());
            cliente.setCPF(entity.getCliente().getCPF());
            cliente.setTelefone(entity.getCliente().getTelefone());
            cliente.setEmail(entity.getCliente().getEmail());
            venda.setCliente(cliente);
        }

        if (entity.getUsuario() != null) {
            Usuario usuario = new Usuario(
                entity.getUsuario().getNome(),
                entity.getUsuario().getEmail(),
                null, null, null, null
            );
            venda.setUsuario(usuario);
        }

        venda.setItensVenda(new ArrayList<>());
        return venda;
    }
}
