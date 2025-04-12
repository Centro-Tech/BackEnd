package school.sptech.projetoMima.dto.itemDto;

import school.sptech.projetoMima.dto.fornecedorDto.FornecedorResponseDto;

public class ItemListDto {
    private String nome;
    private String tamanho;
    private Double preco;
    private Integer qtdEstoque;
    private String codigo;
    private FornecedorResponseDto fornecedor;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public FornecedorResponseDto getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorResponseDto fornecedor) {
        this.fornecedor = fornecedor;
    }
}
