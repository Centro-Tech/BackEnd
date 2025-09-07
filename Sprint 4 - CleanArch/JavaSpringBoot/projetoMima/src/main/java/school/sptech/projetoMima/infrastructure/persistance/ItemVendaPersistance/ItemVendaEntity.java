package school.sptech.projetoMima.infrastructure.persistance.ItemVendaPersistance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import school.sptech.projetoMima.core.domain.item.Item;
import school.sptech.projetoMima.infrastructure.persistance.ClientePersistance.Enitity.ClienteEntity;
import school.sptech.projetoMima.infrastructure.persistance.FornecedorPersistance.FornecedorEntity;
import school.sptech.projetoMima.infrastructure.persistance.UsuarioPersistance.UsuarioEntity;
import school.sptech.projetoMima.infrastructure.persistance.VendaPersistance.VendaEntity;

@Entity
@Schema(description = "Representa um item incluído em uma venda")
public class ItemVendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do item da venda")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fkItem")
    @Schema(description = "Item que está sendo vendido")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "fkItemFornecedor")
    @Schema(description = "Fornecedor do item")
    private FornecedorEntity fornecedor;

    @ManyToOne
    @JoinColumn(name = "fkVenda")
    @JsonBackReference
    @Schema(description = "Venda à qual pertence")
    private VendaEntity venda;

    @ManyToOne
    @JoinColumn(name = "fkVendaCliente")
    @Schema(description = "Cliente da venda")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "fkVendaFuncionario")
    @Schema(description = "Funcionário responsável")
    private UsuarioEntity funcionario;

    @Schema(description = "Quantidade do item")
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

    public FornecedorEntity getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorEntity fornecedor) {
        this.fornecedor = fornecedor;
    }

    public VendaEntity getVenda() {
        return venda;
    }

    public void setVenda(VendaEntity venda) {
        this.venda = venda;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public UsuarioEntity getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(UsuarioEntity funcionario) {
        this.funcionario = funcionario;
    }
}
