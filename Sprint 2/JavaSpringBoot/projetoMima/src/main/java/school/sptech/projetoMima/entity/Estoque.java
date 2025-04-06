package school.sptech.projetoMima.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Schema(description = "Entidade que representa os vestuários disponíveis na loja.")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do vestuário", example = "1")
    private Integer id;

    @Schema(description = "Código de identificação único do vestuário", example = "BLU123M")
    private String codigo;

    @Schema(description = "Quantidade disponível em estoque", example = "10")
    private Integer qtdEstoque;

    @Schema(description = "Nome do vestuário", example = "Camiseta Polo Azul")
    private String nome;

    @Schema(description = "Tamanho do vestuário", example = "M")
    private String tamanho;

    @Schema(description = "Cor do vestuário", example = "Azul")
    private String cor;

    @Schema(description = "Material do vestuário", example = "Poliamida")
    private String material;

    @Schema(description = "Tipo do vestuário", example = "Camiseta")
    private String categoria;

    @Schema(description = "Preço do vestuário", example = "59.99")
    private Double preco;

    @ManyToOne
    @JoinColumn (name = "fkFornecedor")
    @Schema(description = "Fornecedor responsável por este vestuário")
    private Fornecedor fornecedor;

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

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
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
