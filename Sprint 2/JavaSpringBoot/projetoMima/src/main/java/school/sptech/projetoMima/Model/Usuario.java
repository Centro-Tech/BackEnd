package school.sptech.projetoMima.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Classe base para os usuários, contendo nome, email e telefone.")
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do usuário", example = "1", type = "integer", format = "int32")
    private Integer id;

    @NotBlank
    @Size(min = 3, max = 100)
    @Schema(description = "Nome completo do usuário", example = "João da Silva", minLength = 3, maxLength = 100, required = true)
    private String nome;

    @NotBlank
    @Size(min = 5, max = 100)
    @Schema(description = "Endereço de email do usuário", example = "joao@email.com", minLength = 5, maxLength = 100, required = true)
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    @Schema(description = "Número de telefone do usuário (apenas dígitos)", example = "11987654321", minLength = 8, maxLength = 20, required = true)
    private String telefone;

    public Usuario() {
    }

    public Usuario(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
