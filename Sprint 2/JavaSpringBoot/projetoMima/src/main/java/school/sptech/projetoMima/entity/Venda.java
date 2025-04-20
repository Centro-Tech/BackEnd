package school.sptech.projetoMima.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double valorTotal;
    private LocalDate data;

    @ManyToOne
    @JoinColumn (name = "fkCliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn (name = "fkFuncionario")
    private Usuario usuario;

    /* private List<ItensVenda> itens;  */


  /*  public Venda() {
        this.itensVenda = new ArrayList<>();
    } */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getFuncionario() {
        return usuario;
    }

    public void setFuncionario(Usuario usuario) {
        this.usuario = usuario;
    }
}
