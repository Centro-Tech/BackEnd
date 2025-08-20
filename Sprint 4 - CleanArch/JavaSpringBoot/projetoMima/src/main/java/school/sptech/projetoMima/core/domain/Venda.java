package school.sptech.projetoMima.core.domain;

import java.time.LocalDate;
import java.util.List;

public class Venda {

    private final Integer id;
    private final Double valorTotal;
    private final LocalDate data;
    private final Cliente cliente;
    private final Usuario usuario;
    private final List<ItemVenda> itensVenda;

    public Venda(Integer id, Double valorTotal, LocalDate data, Cliente cliente, Usuario usuario, List<ItemVenda> itensVenda) {
        if (valorTotal == null || valorTotal < 0) {
            throw new IllegalArgumentException("Valor total da venda não pode ser nulo ou negativo.");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data da venda não pode ser nula.");
        }
        this.id = id;
        this.valorTotal = valorTotal;
        this.data = data;
        this.cliente = cliente;
        this.usuario = usuario;
        this.itensVenda = itensVenda;
    }

    public Integer getId() {
        return id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public LocalDate getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    @Override
    public String toString() {
        return String.format(
                "Venda{id=%d, valorTotal=%.2f, data=%s, cliente=%s, usuario=%s, itensVenda=%s}",
                id, valorTotal, data, cliente, usuario, itensVenda
        );
    }
}
