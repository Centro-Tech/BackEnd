package school.sptech.projetoMima.core.application.dto.itemVendaDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ItemVendaRequestDto {

    @Schema(description = "ID da venda", example = "1", required = true)
    @NotNull
    private Integer vendaId;

    @Schema(description = "ID do item a ser adicionado", example = "1", required = true)
    @NotNull
    private Integer itemId;

    @Schema(description = "ID do cliente que está adicionando o item", example = "1", required = true)
    @NotNull
    private Integer clienteId;

    @Schema(description = "ID do funcionário responsável", example = "1", required = true)
    @NotNull
    private Integer funcionarioId;

    @Schema(description = "Quantidade do item para vender", example = "2", required = true)
    @NotNull
    @Positive
    private Integer qtdParaVender;

    public Integer getVendaId() {
        return vendaId;
    }

    public void setVendaId(Integer vendaId) {
        this.vendaId = vendaId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQtdParaVender() {
        return qtdParaVender;
    }

    public void setQtdParaVender(Integer qtdParaVender) {
        this.qtdParaVender = qtdParaVender;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId (Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Integer funcionarioId) {
        this.funcionarioId = funcionarioId;
    }
}
