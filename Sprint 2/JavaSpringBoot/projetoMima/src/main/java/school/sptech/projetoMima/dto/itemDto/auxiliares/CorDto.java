package school.sptech.projetoMima.dto.itemDto.auxiliares;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.projetoMima.entity.item.Cor;

public class CorDto {
    @NotBlank
    @NotNull
    @Size(min = 2)
    private String nome;

    @JsonCreator
    public CorDto(String nome) {
        this.nome = nome;
    }

    @JsonValue
    public @NotBlank @NotNull @Size(min = 2) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @NotNull @Size(min = 2) String nome) {
        this.nome = nome;
    }

    public static Cor toEntity (CorDto cor) {
        Cor response = new Cor();
        response.setNome(cor.getNome());
        return response;
    }
}
