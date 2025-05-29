package school.sptech.projetoMima.dto.itemDto.auxiliares;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.projetoMima.entity.item.Tamanho;

@Schema(description = "DTO para tamanho do item")
public class TamanhoDto {

    @Schema(description = "Tamanho do item", example = "M", required = true)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 2)
    private String tamanho;

    public TamanhoDto() {}

    public TamanhoDto(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public static Tamanho toEntity(TamanhoDto tamanho) {
        Tamanho response = new Tamanho();
        response.setTamanho(tamanho.getTamanho());
        return response;
    }
}
