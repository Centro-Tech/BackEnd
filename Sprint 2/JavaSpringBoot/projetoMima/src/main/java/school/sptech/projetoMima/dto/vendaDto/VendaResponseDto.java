package school.sptech.projetoMima.dto.vendaDto;

import school.sptech.projetoMima.entity.ItemVenda;

import java.util.List;

public class VendaResponseDto {
    private Double valorTotal;
    private List<ItemVenda> itensVenda;

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }
}
