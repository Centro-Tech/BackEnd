package school.sptech.projetoMima.dto.itemDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.projetoMima.entity.Fornecedor;

@Schema(name = "ItemRequestDto", example = """
{
  "nome": "Camiseta Estampada",
  "qtdEstoque": 50,
  "preco": 79.90,
  "idTamanho": 2,
  "idCor": 5,
  "idMaterial": 3,
  "idCategoria": 1,
  "fornecedor": {
    "id": 1,
    "nome": "Empresa XYZ LTDA",
    "telefone": "11987654321",
    "email": "contato@empresa.com"
  }
}
""")
public class ItemRequestDto {

    @NotBlank
    @NotNull
    @Size(max = 100, min = 1)
    private String nome;

    @NotNull
    @DecimalMin(value = "1.0")
    private Integer qtdEstoque;

    @NotNull
    @DecimalMin(value = "1.0")
    private Double preco;

    @NotNull
    private Integer idTamanho;

    @NotNull
    private Integer idCor;

    @NotNull
    private Integer idMaterial;

    @NotNull
    private Integer idCategoria;

    @NotNull
    private Fornecedor fornecedor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getIdTamanho() {
        return idTamanho;
    }

    public void setIdTamanho(Integer idTamanho) {
        this.idTamanho = idTamanho;
    }

    public Integer getIdCor() {
        return idCor;
    }

    public void setIdCor(Integer idCor) {
        this.idCor = idCor;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
