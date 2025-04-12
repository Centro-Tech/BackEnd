package school.sptech.projetoMima.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import school.sptech.projetoMima.versãoAntiga.Usuario;

@Entity
@Schema(description = "Entidade que representa um funcionário, herdando os dados básicos de um usuário. Contém o cargo ocupado pelo funcionário dentro da empresa.")
public class Funcionario extends Usuario {

    @NotBlank(message = "O cargo é obrigatório")
    @Size(min = 2, max = 50, message = "O cargo deve ter entre 2 e 50 caracteres")
    @Schema(description = "Cargo do funcionário dentro da empresa, como por exemplo 'Atendente', 'Gerente de vendas' ou 'Estoquista'", example = "Gerente de vendas", type = "string", minLength = 2, maxLength = 50, required = true)
    private String cargo;

    public Funcionario() {}

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
