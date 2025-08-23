package school.sptech.projetoMima.core.adapter.venda;

import school.sptech.projetoMima.core.adapter.itemVenda.ItemVendaResponseDto;
import java.util.List;

public class VendaResponseDto {
    private Double valorTotal;
    private List<ItemVendaResponseDto> itensVenda;

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemVendaResponseDto> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVendaResponseDto> itensVenda) {
        this.itensVenda = itensVenda;
    }
}
