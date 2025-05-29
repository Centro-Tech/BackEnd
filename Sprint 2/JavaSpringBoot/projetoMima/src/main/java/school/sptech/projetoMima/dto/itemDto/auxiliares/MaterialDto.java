package school.sptech.projetoMima.dto.itemDto.auxiliares;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.projetoMima.entity.item.Material;

@Schema(description = "DTO para material do item")
public class MaterialDto {

    @Schema(description = "Nome do material", example = "Algodão", required = true)
    @NotBlank
    @NotNull
    @Size(min = 2)
    private String material;

    public MaterialDto() {}

    public MaterialDto(String material) {
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public static Material toEntity(MaterialDto material) {
        Material response = new Material();
        response.setMaterial(material.getMaterial());
        return response;
    }
}
