package school.sptech.projetoMima.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EstoqueResponseDto {
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
    @Size(max = 8, min = 7)
    private String codigo;

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

    public @NotBlank @NotNull @Size(max = 8, min = 7) String getCodigo() {
        return codigo;
    }

    public void setCodigo(@NotBlank @NotNull @Size(max = 8, min = 7) String codigo) {
        this.codigo = codigo;
    }
}
