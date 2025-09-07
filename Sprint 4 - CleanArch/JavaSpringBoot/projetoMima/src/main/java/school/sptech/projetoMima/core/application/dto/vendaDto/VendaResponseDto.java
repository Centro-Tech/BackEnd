package school.sptech.projetoMima.core.application.dto.vendaDto;

import school.sptech.projetoMima.core.domain.ItemVenda;

import java.time.LocalDate;
import java.util.List;

public class VendaResponseDto {
    private Integer id;
    private Integer clienteId;
    private Integer usuarioId;
    private Double valorTotal;
    private LocalDate data;
    private List<ItemVenda> itensVenda;

    public VendaResponseDto() {}

    public VendaResponseDto(Integer id, Integer clienteId, Double valorTotal, List<ItemVenda> itensVenda) {
        this.id = id;
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
        this.itensVenda = itensVenda;
    }

    public Integer getId() { return id; }
    public Integer getClienteId() { return clienteId; }
    public Integer getUsuarioId() { return usuarioId; }
    public Double getValorTotal() { return valorTotal; }
    public LocalDate getData() { return data; }
    public List<ItemVenda> getItensVenda() { return itensVenda; }

    public void setId(Integer id) { this.id = id; }
    public void setClienteId(Integer clienteId) { this.clienteId = clienteId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
    public void setData(LocalDate data) { this.data = data; }
    public void setItensVenda(List<ItemVenda> itensVenda) { this.itensVenda = itensVenda; }
}
