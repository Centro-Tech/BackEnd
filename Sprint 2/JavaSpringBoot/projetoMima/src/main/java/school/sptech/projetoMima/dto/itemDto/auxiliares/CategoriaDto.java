package school.sptech.projetoMima.dto.itemDto.auxiliares;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.projetoMima.entity.item.Categoria;

public class CategoriaDto {
    @NotNull
    @NotBlank
    @Size(min = 2)
    private String tipo;

    @JsonCreator
    public CategoriaDto(String tipo) {
        this.tipo = tipo;
    }

    @JsonValue
    public @NotNull @NotBlank @Size(min = 2) String getTipo() {
        return tipo;
    }

    public void setTipo(@NotNull @NotBlank @Size(min = 2) String tipo) {
        this.tipo = tipo;
    }

    public static Categoria toEntity(CategoriaDto categoria) {
        Categoria response = new Categoria();
        response.setNome(categoria.getTipo());
        return response;
    }
}
