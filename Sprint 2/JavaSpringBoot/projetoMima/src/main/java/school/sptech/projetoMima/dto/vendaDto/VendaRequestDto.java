package school.sptech.projetoMima.dto.vendaDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import school.sptech.projetoMima.entity.Cliente;
import school.sptech.projetoMima.entity.ItemVenda;

import java.util.List;

public class VendaRequestDto {

    @Schema(description = "Valor total da venda", example = "199.99", required = true)
    @NotNull
    @PositiveOrZero
    private Double valorTotal;

    @Schema(description = "Cliente que está realizando a compra", required = true)
    @NotNull
    @Valid
    private Integer clienteId;

    @Schema(description = "Lista de itens que compõem a venda", required = true)
    @NotEmpty
    @Valid
    private List<Integer> itensVenda;

    public @NotNull @PositiveOrZero Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(@NotNull @PositiveOrZero Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public @NotNull @Valid Integer getCliente() {
        return clienteId;
    }

    public void setCliente(@NotNull @Valid Integer cliente) {
        this.clienteId = cliente;
    }

    public @NotEmpty @Valid List<Integer> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(@NotEmpty @Valid List<Integer> itensVenda) {
        this.itensVenda = itensVenda;
    }
}
