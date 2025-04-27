package school.sptech.projetoMima.dto.vendaDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import school.sptech.projetoMima.entity.Cliente;
import school.sptech.projetoMima.entity.ItemVenda;

import java.util.List;

public class VendaRequestDto {
    @NotNull
    @PositiveOrZero
    private Double valorTotal;

    @NotNull
    @Valid
    private Cliente cliente;

    @NotEmpty
    @Valid
    private List<ItemVenda> itensVenda;

    public @NotNull @PositiveOrZero Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(@NotNull @PositiveOrZero Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public @NotNull @Valid Cliente getCliente() {
        return cliente;
    }

    public void setCliente(@NotNull @Valid Cliente cliente) {
        this.cliente = cliente;
    }

    public @NotEmpty @Valid List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(@NotEmpty @Valid List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }
}
