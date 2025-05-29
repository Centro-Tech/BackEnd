package school.sptech.projetoMima.dto.itemDto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import school.sptech.projetoMima.dto.vendaDto.VendaRequestDto;
import school.sptech.projetoMima.entity.ItemVenda;

public class ItemVendaRequestDto {

    @NotNull
    @Valid
    private VendaRequestDto venda;

    @NotNull
    @Valid
    private ItemVenda itemVenda;

    public VendaRequestDto getVenda() {
        return venda;
    }

    public void setVenda(VendaRequestDto venda) {
        this.venda = venda;
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }
}
