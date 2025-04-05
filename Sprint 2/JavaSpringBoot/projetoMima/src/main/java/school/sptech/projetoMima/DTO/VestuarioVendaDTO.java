package school.sptech.projetoMima.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "DTO que representa um vestuário vendido")
public class VestuarioVendaDTO {

    @Schema(description = "Código único de identificação do vestuário", example = "BL1234567M")
    private String codigoIdentificacao;

    @Schema(description = "Nome do vestuário", example = "Blusa Cropped")
    private String nome;

    @Schema(description = "Tamanho do vestuário", example = "M")
    private String tamanho;

    @Schema(description = "Quantidade total de unidades vendidas", example = "3")
    private Integer quantidadeVendida;

    @Schema(description = "Lista de datas em que o vestuário foi vendido")
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
