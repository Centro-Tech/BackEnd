package school.sptech.projetoMima.entity;

import jakarta.persistence.*;
import school.sptech.projetoMima.entity.item.Item;

@Entity
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fkItem")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "fkItemFornecedor")
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "fkVenda")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "fkVendaCliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fkVendaFuncionario")
    private Usuario funcionario;
    private Integer qtdParaVender;

    public Integer getQtdParaVender() {
        return qtdParaVender;
    }

    public void setQtdParaVender(Integer qtdParaVender) {
        this.qtdParaVender = qtdParaVender;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Usuario funcionario) {
        this.funcionario = funcionario;
    }
}
