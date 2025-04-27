package school.sptech.projetoMima.dto.itemDto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.projetoMima.entity.Fornecedor;
import school.sptech.projetoMima.entity.item.Categoria;
import school.sptech.projetoMima.entity.item.Cor;
import school.sptech.projetoMima.entity.item.Material;
import school.sptech.projetoMima.entity.item.Tamanho;

public class ItemRequestDto {
    @NotBlank
    @NotNull
    @Size(max = 100, min = 1)
    private String nome;

    @NotNull
    @NotBlank
    @DecimalMin(value = "1.0")
    private Integer qtdEstoque;

    @NotBlank
    @NotNull
    @Size(max = 2, min = 1)
    private Tamanho tamanho;

    @NotBlank
    @NotNull
    @Size(max = 45, min = 1)
    private Cor cor;

    @NotBlank
    @NotNull
    @Size(max = 45, min = 1)
    private Material material;

    @NotBlank
    @NotNull
    @Size(max = 45, min = 1)
    private Categoria categoria;

    @NotBlank
    @NotNull
    @DecimalMin(value = "1.0")
    private Double preco;

    @NotBlank
    @NotNull
    private Fornecedor fornecedor;

    public @NotBlank @NotNull @Size(max = 100, min = 1) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @NotNull @Size(max = 100, min = 1) String nome) {
        this.nome = nome;
    }

    public @NotNull @NotBlank @DecimalMin(value = "1.0") Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(@NotNull @NotBlank @DecimalMin(value = "1.0") Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public @NotBlank @NotNull @Size(max = 2, min = 1) Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(@NotBlank @NotNull @Size(max = 2, min = 1) Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public @NotBlank @NotNull @Size(max = 45, min = 1) Cor getCor() {
        return cor;
    }

    public void setCor(@NotBlank @NotNull @Size(max = 45, min = 1) Cor cor) {
        this.cor = cor;
    }

    public @NotBlank @NotNull @Size(max = 45, min = 1) Material getMaterial() {
        return material;
    }

    public void setMaterial(@NotBlank @NotNull @Size(max = 45, min = 1) Material material) {
        this.material = material;
    }

    public @NotBlank @NotNull @Size(max = 45, min = 1) Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(@NotBlank @NotNull @Size(max = 45, min = 1) Categoria categoria) {
        this.categoria = categoria;
    }

    public @NotBlank @NotNull @DecimalMin(value = "1.0") Double getPreco() {
        return preco;
    }

    public void setPreco(@NotBlank @NotNull @DecimalMin(value = "1.0") Double preco) {
        this.preco = preco;
    }

    public @NotBlank @NotNull Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(@NotBlank @NotNull Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
