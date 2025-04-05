package school.sptech.projetoMima.Model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Schema(description = "Entidade que representa um vestuário disponível na loja.")
public class Vestuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do vestuário", example = "1")
    private Integer id;

    @NotBlank
    @Schema(description = "Código de identificação único do vestuário", example = "ABC123")
    private String codigoIdentificacao;

    @NotBlank
    @Size(min = 2, max = 100)
    @Schema(description = "Nome do vestuário", example = "Camiseta Polo Azul")
    private String nome;

    @NotBlank
    @Schema(description = "Tamanho do vestuário", example = "M")
    private String tamanho;

    @NotBlank
    @Schema(description = "Cor do vestuário", example = "Azul")
    private String cor;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Preço do vestuário", example = "59.99")
    private Double preco;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Quantidade disponível em estoque", example = "10")
    private Integer quantidade;

    @Schema(description = "Indica se o vestuário foi vendido", example = "false")
    private Boolean vendido;

    @NotNull
    @Schema(description = "ID do fornecedor responsável por este vestuário", example = "2")
    private Integer fornecedorId;

    @Schema(description = "Data em que o vestuário foi registrado no sistema", example = "2024-03-01")
    private LocalDate dataRegistro;

    @Schema(description = "Data da última venda do vestuário", example = "2024-03-15")
    private LocalDate dataVenda;

    @ElementCollection
    @Schema(description = "Lista de datas em que o vestuário foi vendido", type = "array", example = "[\"2024-03-01\", \"2024-03-15\"]")
    private List<LocalDate> datasVendas = new ArrayList<>();





    @Schema(description = "Quantidade total de unidades vendidas", example = "5")
    private Integer quantidadeVendida = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoIdentificacao() {
        return codigoIdentificacao;
    }

    public void setCodigoIdentificacao(String codigoIdentificacao) {
        this.codigoIdentificacao = codigoIdentificacao;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean getVendido() {
        return vendido;
    }

    public void setVendido(Boolean vendido) {
        this.vendido = vendido;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public List<LocalDate> getDatasVendas() {
        return datasVendas;
    }

    public void setDatasVendas(List<LocalDate> datasVendas) {
        this.datasVendas = datasVendas;
    }

    public Integer getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(Integer quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public Integer getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Integer fornecedorId) {
        this.fornecedorId = fornecedorId;
    }
}
