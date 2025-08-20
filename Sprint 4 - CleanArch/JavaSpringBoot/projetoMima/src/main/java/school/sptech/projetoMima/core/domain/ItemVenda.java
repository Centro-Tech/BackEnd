package school.sptech.projetoMima.core.domain;

import school.sptech.projetoMima.core.domain.item.Item;

public class ItemVenda {

    private final Integer id;
    private final Item item;
    private final Fornecedor fornecedor;
    private final Venda venda;
    private final Cliente cliente;
    private final Usuario funcionario;
    private final Integer qtdParaVender;

    public ItemVenda(Integer id, Item item, Fornecedor fornecedor, Venda venda,
                     Cliente cliente, Usuario funcionario, Integer qtdParaVender) {
        if (qtdParaVender == null || qtdParaVender <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        this.id = id;
        this.item = item;
        this.fornecedor = fornecedor;
        this.venda = venda;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.qtdParaVender = qtdParaVender;
    }

    public Integer getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public Venda getVenda() {
        return venda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public Integer getQtdParaVender() {
        return qtdParaVender;
    }

    @Override
    public String toString() {
        return String.format(
                "ItemVenda{id=%d, item=%s, fornecedor=%s, venda=%s, cliente=%s, funcionario=%s, qtdParaVender=%d}",
                id, item, fornecedor, venda, cliente, funcionario, qtdParaVender
        );
    }
}
