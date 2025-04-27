package school.sptech.projetoMima.dto.itemDto.auxiliares;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.projetoMima.entity.item.Material;
import school.sptech.projetoMima.entity.item.Tamanho;

public class TamanhoDto {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 2)
    private String tamanho;

    public @NotNull @NotBlank @Size(min = 1, max = 2) String getTamanho() {
        return tamanho;
    }

    public void setTamanho(@NotNull @NotBlank @Size(min = 1, max = 2) String tamanho) {
        this.tamanho = tamanho;
    }

    public static Tamanho toEntity(TamanhoDto tamanho) {
        Tamanho response = new Tamanho();
        response.setTamanho(tamanho.getTamanho());
        return response;
    }
}
