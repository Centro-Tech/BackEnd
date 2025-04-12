package school.sptech.projetoMima.versãoAntiga;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Classe base para os usuários, contendo nome, CPF, email, telefone e endereço.")
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
    @Size(min = 11, max = 11)
    @Schema(description = "CPF do usuário (somente números)", example = "12345678900", minLength = 11, maxLength = 11, required = true)
    private String cpf;

    @NotBlank
    @Size(min = 5, max = 100)
    @Schema(description = "Endereço de email do usuário", example = "joao@email.com", minLength = 5, maxLength = 100, required = true)
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    @Schema(description = "Número de telefone do usuário (apenas dígitos)", example = "11987654321", minLength = 8, maxLength = 20, required = true)
    private String telefone;

    @NotBlank
    @Size(min = 5, max = 255)
    @Schema(description = "Endereço físico do usuário", example = "Rua das Flores, 123 - Centro", minLength = 5, maxLength = 255, required = true)
    private String endereco;

    public Usuario() {
    }

    public Usuario(String nome, String cpf, String email, String telefone, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
