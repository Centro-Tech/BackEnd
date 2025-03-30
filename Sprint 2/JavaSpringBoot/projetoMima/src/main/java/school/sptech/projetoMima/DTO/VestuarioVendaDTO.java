package school.sptech.projetoMima.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class VestuarioVendaDTO {
    private String codigoIdentificacao;
    private String nome;
    private String tamanho;
    private Integer quantidadeVendida;
    private List<LocalDate> datasDeVenda;

    public VestuarioVendaDTO(String codigoIdentificacao, String nome, String tamanho, Integer quantidadeVendida, List<LocalDate> datasDeVenda) {
        this.codigoIdentificacao = codigoIdentificacao;
        this.nome = nome;
        this.tamanho = tamanho;
        this.quantidadeVendida = quantidadeVendida;
        this.datasDeVenda = datasDeVenda;
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

    public Integer getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(Integer quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public List<LocalDate> getDatasDeVenda() {
        return datasDeVenda;
    }

    public void setDatasDeVenda(List<LocalDate> datasDeVenda) {
        this.datasDeVenda = datasDeVenda;
    }
}
