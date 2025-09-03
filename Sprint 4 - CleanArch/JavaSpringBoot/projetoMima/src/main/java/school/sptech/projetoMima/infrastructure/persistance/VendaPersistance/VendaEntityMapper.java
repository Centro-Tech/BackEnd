package school.sptech.projetoMima.infrastructure.persistance.VendaPersistance;

import school.sptech.projetoMima.core.domain.Venda;

public class VendaEntityMapper {

    public static VendaEntity toEntity (Venda venda){
        VendaEntity vendaEntity = new VendaEntity();
        vendaEntity.setId(venda.getId());
        vendaEntity.setCliente(venda.getCliente());
        vendaEntity.setData(venda.getData());
        vendaEntity.setFuncionario(venda.getFuncionario());
        vendaEntity.setItensVenda(venda.getItensVenda());
        vendaEntity.setValorTotal(venda.getValorTotal());
        return vendaEntity;
    }

    public static Venda toDomain(VendaEntity entity){
        Venda venda = new Venda();
        venda.setId(entity.getId());
        venda.setCliente(entity.getCliente());
        venda.setData(entity.getData());
        venda.setFuncionario(entity.getFuncionario());
        venda.setItensVenda(entity.getItensVenda());
        venda.setValorTotal(entity.getValorTotal());
        return venda;
    }

}
