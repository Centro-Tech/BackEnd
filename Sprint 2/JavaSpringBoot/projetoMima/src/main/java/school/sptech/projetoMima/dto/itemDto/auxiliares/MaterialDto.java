package school.sptech.projetoMima.dto.itemDto.auxiliares;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.projetoMima.entity.item.Material;

public class MaterialDto {
    @NotBlank
    @NotNull
    @Size(min = 2)
    private String material;

    @JsonCreator
    public MaterialDto(String material) {
        this.material = material;
    }

    @JsonValue
    public @NotBlank @NotNull @Size(min = 2) String getMaterial() {
        return material;
    }

    public void setMaterial(@NotBlank @NotNull @Size(min = 2) String material) {
        this.material = material;
    }

    public static Material toEntity(MaterialDto material) {
        Material response = new Material();
        response.setMaterial(material.getMaterial());
        return response;
    }
}
