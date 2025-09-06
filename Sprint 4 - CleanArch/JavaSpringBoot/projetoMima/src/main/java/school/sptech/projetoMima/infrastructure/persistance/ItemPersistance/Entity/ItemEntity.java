package school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.Entity;

import jakarta.persistence.*;
import school.sptech.projetoMima.core.domain.Fornecedor;

@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String codigo;

    @Column(nullable = false)
    private Integer qtdEstoque;

    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fkTamanho")
    private TamanhoEntity tamanho;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fkCor")
    private CorEntity cor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fkMaterial")
    private MaterialEntity material;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fkCategoria")
    private CategoriaEntity categoria;

    @Column(nullable = false)
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "fkFornecedor")
    private Fornecedor fornecedor;

    public ItemEntity() {}

    public ItemEntity(Integer id, String codigo, Integer qtdEstoque, String nome,
                     TamanhoEntity tamanho, CorEntity cor, MaterialEntity material,
                     CategoriaEntity categoria, Double preco, Fornecedor fornecedor) {
        this.id = id;
        this.codigo = codigo;
        this.qtdEstoque = qtdEstoque;
        this.nome = nome;
        this.tamanho = tamanho;
        this.cor = cor;
        this.material = material;
        this.categoria = categoria;
        this.preco = preco;
        this.fornecedor = fornecedor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TamanhoEntity getTamanho() {
        return tamanho;
    }

    public void setTamanho(TamanhoEntity tamanho) {
        this.tamanho = tamanho;
    }

    public CorEntity getCor() {
        return cor;
    }

    public void setCor(CorEntity cor) {
        this.cor = cor;
    }

    public MaterialEntity getMaterial() {
        return material;
    }

    public void setMaterial(MaterialEntity material) {
        this.material = material;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
