package school.sptech.projetoMima.Model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Entidade que representa um funcionário, herdando os dados básicos de um usuário. Contém o cargo ocupado pelo funcionário dentro da empresa.")
public class Funcionario extends Usuario {

    @NotBlank(message = "O cargo é obrigatório")
    @Size(min = 2, max = 50, message = "O cargo deve ter entre 2 e 50 caracteres")
    @Schema(description = "Cargo do funcionário dentro da empresa, como por exemplo 'Atendente', 'Gerente de vendas' ou 'Estoquista'", example = "Gerente de vendas", type = "string", minLength = 2, maxLength = 50, required = true)
    private String cargo;

    public Funcionario() {}

    public Funcionario(String nome, String email, String telefone, String cargo) {
        super(nome, email, telefone);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
